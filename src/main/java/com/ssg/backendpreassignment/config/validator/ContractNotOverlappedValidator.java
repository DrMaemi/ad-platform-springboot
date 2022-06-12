package com.ssg.backendpreassignment.config.validator;

import com.ssg.backendpreassignment.dto.ContractDto;
import com.ssg.backendpreassignment.service.ContractService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * 계약 생성 시 업체별 계약 기간이 중복되지 않도록 유효성 검사
 * 계약이 생성되지 않은 업체거나 계약 기간이 만료된 업체인지 검사
 */
@RequiredArgsConstructor
public class ContractNotOverlappedValidator implements ConstraintValidator<ContractNotOverlapped, Long> {
    private final ContractService contractService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        ContractDto contractDto = contractService.findByCompanyId(value);

        if (contractDto == null) {
            return true;
        } else if (contractDto.getEndDate().isBefore(LocalDate.now())) {
            return true;
        }

        return false;
    }
}

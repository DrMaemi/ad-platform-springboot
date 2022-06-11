package com.ssg.backendpreassignment.config.validator;

import com.ssg.backendpreassignment.dto.ContractDto;
import com.ssg.backendpreassignment.service.ContractService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

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

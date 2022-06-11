package com.ssg.backendpreassignment.config.validator;

import com.ssg.backendpreassignment.dto.ContractDto;
import com.ssg.backendpreassignment.service.ContractService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

@RequiredArgsConstructor
public class InContractValidator implements ConstraintValidator<InContract, Long> {
    private final ContractService contractService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        ContractDto contractDto = contractService.findByCompanyId(value);

        if (contractDto == null || contractDto.getEndDate().isBefore(LocalDate.now())) {
            return false;
        }

        return true;
    }
}

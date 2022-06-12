package com.ssg.backendpreassignment.config.validator;

import com.ssg.backendpreassignment.service.CompanyService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class RegisteredCompanyValidator implements ConstraintValidator<RegisteredCompany, Long> {
    private final CompanyService companyService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return companyService.isRegistered(value);
    }
}

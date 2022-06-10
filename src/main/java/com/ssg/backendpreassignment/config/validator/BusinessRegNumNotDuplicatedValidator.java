package com.ssg.backendpreassignment.config.validator;

import com.ssg.backendpreassignment.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class BusinessRegNumNotDuplicatedValidator implements ConstraintValidator<BusinessRegNumNotDuplicated, String> {
    private final CompanyRepository companyRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || !companyRepository.existsByBusinessRegistrationNumber(value);
    }
}

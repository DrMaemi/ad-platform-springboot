package com.ssg.backendpreassignment.config.validator;

import com.ssg.backendpreassignment.service.CompanyService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 계약 생성 시 해당 업체가 등록되어 있는지 검사
 */
@RequiredArgsConstructor
public class RegisteredCompanyValidator implements ConstraintValidator<RegisteredCompany, Long> {
    private final CompanyService companyService;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return companyService.isRegistered(value);
    }
}

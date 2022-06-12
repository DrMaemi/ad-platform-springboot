package com.ssg.backendpreassignment.config.validator;

import com.ssg.backendpreassignment.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 업체 등록 시 사업자번호 유효성 검사 진행
 * 입력된 데이터가 있다면 사업자번호가 DB에 등록되어있는지 아닌지 검사
 */
@RequiredArgsConstructor
public class BusinessRegNumNotDuplicatedValidator implements ConstraintValidator<BusinessRegNumNotDuplicated, String> {
    private final CompanyRepository companyRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || !companyRepository.existsByBusinessRegistrationNumber(value);
    }
}

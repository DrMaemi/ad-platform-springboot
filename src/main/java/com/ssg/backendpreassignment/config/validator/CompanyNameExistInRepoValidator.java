package com.ssg.backendpreassignment.config.validator;

import com.ssg.backendpreassignment.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 업체 등록 시 상품 리스트에 셋팅된 업체명인지 아닌지 유효성 검사 진행
 * 입력된 업체명에 대해 DB에 등록되어 있는지 검사
 */
@RequiredArgsConstructor
public class CompanyNameExistInRepoValidator implements ConstraintValidator<CompanyNameExistInRepo, String> {
    private final CompanyRepository companyRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return companyRepository.existsByName(value);
    }
}

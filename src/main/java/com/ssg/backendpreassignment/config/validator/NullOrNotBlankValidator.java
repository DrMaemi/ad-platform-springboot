package com.ssg.backendpreassignment.config.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 엔티티 수정 시 입력 필드가 null이거나 공백이 아닌 경우 유효
 */
public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
    private boolean isStringNullOrNotBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !isStringNullOrNotBlank(value);
    }
}

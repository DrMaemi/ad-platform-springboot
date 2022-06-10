package com.ssg.backendpreassignment.config.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlank, String> {
    private boolean isStringNullOrNotBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !isStringNullOrNotBlank(value);
    }
}

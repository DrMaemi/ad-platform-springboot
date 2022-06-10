package com.ssg.backendpreassignment.config.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumValidator implements ConstraintValidator<PhoneNum, String> {

    private boolean isNumber(String str) {
        if (str.isEmpty()) return false;

        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String[] splited = value.split("-");

        if (
                splited.length == 3 && 1 <= splited[0].length() && 1 <= splited[1].length() && 1 <= splited[2].length()
                        && isNumber(splited[0]) && isNumber(splited[1]) && isNumber(splited[2])
        ) {
            return true;
        }

        return false;
    }
}

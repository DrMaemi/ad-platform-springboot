package com.ssg.backendpreassignment.config.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 업체 등록 시 업체전화번호 유효성 검사 진행
 * 입력된 데이터가 두 개의 '-'로 구분되고 숫자로 이루어져 있는지 검사
 */
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

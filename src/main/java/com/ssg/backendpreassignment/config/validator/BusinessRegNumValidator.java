package com.ssg.backendpreassignment.config.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 업체 등록 시 사업자번호 유효성 검사 진행
 * 입력된 데이터가 두 개의 '-'로 구분되고 총 10자리이며 숫자로 이루어져있는지 검사
 */
public class BusinessRegNumValidator implements ConstraintValidator<BusinessRegNum, String> {

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
                splited.length == 3 && splited[0].length() == 3 && splited[1].length() == 2 && splited[2].length() == 5
                && isNumber(splited[0]) && isNumber(splited[1]) && isNumber(splited[2])
        ) {
            return true;
        }

        return false;
    }
}

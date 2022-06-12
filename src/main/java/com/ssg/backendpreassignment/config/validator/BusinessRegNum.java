package com.ssg.backendpreassignment.config.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 업체 등록 시 입력된 사업자번호의 자리수와 입력 값이 숫자로만 이루어져있는지에 대한 유효성 검사를 위한 어노테이션 정의
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy=BusinessRegNumValidator.class)
@Documented
public @interface BusinessRegNum {
    String message() default "사업자번호는 XXX-XX-XXXXX 형태로 총 10개의 숫자를 입력해주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List{
        BusinessRegNum value();
    }
}

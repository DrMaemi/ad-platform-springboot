package com.ssg.backendpreassignment.config.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 업체 등록 시 입력된 업체전화번호의 자리수와 입력 값이 숫자로만 이루어져있는지에 대한 유효성 검사를 위한 어노테이션 정의
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy=PhoneNumValidator.class)
@Documented
public @interface PhoneNum {
    String message() default "업체전화번호는 XXX-XXX-XXXX와 같이 '-'를 이용해 구분하여 숫자를 입력해주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List{
        PhoneNum value();
    }
}

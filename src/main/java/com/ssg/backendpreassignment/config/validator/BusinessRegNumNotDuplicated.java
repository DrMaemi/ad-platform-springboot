package com.ssg.backendpreassignment.config.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 업체 당 고유한 사업자번호를 가질 수 있도록 유효성 검사를 위한 어노테이션 정의
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy= BusinessRegNumNotDuplicatedValidator.class)
@Documented
public @interface BusinessRegNumNotDuplicated {
    String message() default "이미 등록된 사업자번호입니다. 다시 한 번 확인해주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List{
        BusinessRegNumNotDuplicated value();
    }
}

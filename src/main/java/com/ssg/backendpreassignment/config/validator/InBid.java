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
 * 광고과금 데이터 생성 API 호출 시 등록되지 않은 광고입찰 ID인지 아닌지 검사하기 위한 유효성 검사 어노테이션 정의
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy=InBidValidator.class)
@Documented
public @interface InBid {
    String message() default "등록되지 않은 광고입찰 ID입니다. 다시 한 번 확인해주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List{
        InBid value();
    }
}

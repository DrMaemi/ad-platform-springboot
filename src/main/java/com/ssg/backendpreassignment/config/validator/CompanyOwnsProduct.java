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
 * 광고 입찰 생성 시 입력된 업체 ID와 상품 ID에 대해 해당 업체가 해당 상품을 소유하고 있는지 검사하기 위한 유효성 검사 어노테이션 정의
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy=CompanyOwnsProductValidator.class)
@Documented
public @interface CompanyOwnsProduct {
    String message() default "업체가 해당 상품을 소유하고 있지 않습니다. 업체와 상품의 소유 관계를 다시 한 번 확인해주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List{
        CompanyOwnsProduct value();
    }
}

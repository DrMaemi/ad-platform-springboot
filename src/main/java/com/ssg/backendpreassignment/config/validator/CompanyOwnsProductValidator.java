package com.ssg.backendpreassignment.config.validator;

import com.ssg.backendpreassignment.dto.ProductDto;
import com.ssg.backendpreassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ServerErrorException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * 광고 입찰 생성 시 입력된 업체 ID와 상품 ID에 대해 해당 업체가 해당 상품을 소유하고 있는지 검사
 * 입력받은 상품 ID로부터 상품 엔티티를 조회하고 해당 엔티티의 업체 ID가 입력받은 업체 ID와 일치하는지 검사
 */
@RequiredArgsConstructor
public class CompanyOwnsProductValidator implements ConstraintValidator<CompanyOwnsProduct, Object> {
    private final ProductService productService;
    private String message;

    @Override
    public void initialize(CompanyOwnsProduct constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Long companyId = (long)getFieldValue(value, "companyId");
        Long productId = (long)getFieldValue(value, "productId");
        ProductDto productDto = productService.getProduct(productId);

        if (productDto.getCompanyDto().getId().equals(companyId)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("productId")
                .addConstraintViolation();
        return false;
    }

    // 리플렉션을 이용하여 필드를 가져온다
    private Object getFieldValue(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        Field dateField;
        try {
            dateField = clazz.getDeclaredField(fieldName);
            dateField.setAccessible(true);
            Object target = dateField.get(object);
            return target;
        } catch (NoSuchFieldException e) {
            System.out.println(e.getStackTrace());
        } catch (IllegalAccessException e) {
            System.out.println(e.getStackTrace());
        }
        throw new ServerErrorException("Not Found Field");
    }
}

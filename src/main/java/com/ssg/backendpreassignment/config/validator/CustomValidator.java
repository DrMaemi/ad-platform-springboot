package com.ssg.backendpreassignment.config.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomValidator implements Validator {
    private final SpringValidatorAdapter validator;

    @Override
    public boolean supports(Class<?> _class) {
        return List.class.equals(_class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        for (Object object: (Collection)target) {
            validator.validate(object, errors);
        }
    }
}

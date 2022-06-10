package com.ssg.backendpreassignment.config.advice;

import com.ssg.backendpreassignment.config.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice(basePackages="com.ssg.backendpreassignment")
public class RestAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationExceptionHandler(ConstraintViolationException e, HttpServletRequest request) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<Map<String, Object>> validateRes = new ArrayList<>();

        for (ConstraintViolation v: violations) {
            Map<String, Object> eachRes = new HashMap<>();
            String field = String.join(".", v.getPropertyPath().toString().split("\\."));
            Object rejectedValue = v.getInvalidValue();
            String message = v.getMessage();
            eachRes.put("field", field);
            eachRes.put("rejectedValue", rejectedValue);
            eachRes.put("message", message);
            validateRes.add(eachRes);
        }

        return new ResponseEntity(RestResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .result(validateRes)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<ObjectError> objectErrors = e.getAllErrors();
        List<Map<String, Object>> validateRes = new ArrayList<>();

        for (ObjectError objectError: objectErrors) {
            FieldError fieldError = (FieldError)objectError;
            Map<String, Object> eachRes = new HashMap<>();
            eachRes.put("field", fieldError.getField());
            eachRes.put("rejectedValue", fieldError.getRejectedValue());
            eachRes.put("message", fieldError.getDefaultMessage());
            validateRes.add(eachRes);
        }

        return new ResponseEntity(RestResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .result(validateRes)
                .build(), HttpStatus.BAD_REQUEST);
    }
}

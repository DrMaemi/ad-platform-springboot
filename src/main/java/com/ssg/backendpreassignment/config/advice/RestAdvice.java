package com.ssg.backendpreassignment.config.advice;

import com.ssg.backendpreassignment.config.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * com.ssg.backendpreassignment 패키지에서 발생할 수 있는 Exception들을 처리하는 ExceptionHandler 메서드들을 모아놓은 클래스
 */
@RestControllerAdvice(basePackages="com.ssg.backendpreassignment")
public class RestAdvice {

    /**
     * Spring Validation이 제공하는 유효성 검사 기본 어노테이션에 의한 ConstraintViolationException을 처리하는 메서드
     * Javax bean validation framework에 의해 발생하는 Exception
     * 유효성 검증에 실패한 필드(field)와 값(rejectedValue), 그에 대한 설명 메세지(message)를 반환한다.
     *
     * @param e
     * @param request
     * @return ResponseEntity
     */
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

    /**
     * 비즈니스 로직에 필요한 유효성 검사를 위해 별도로 작성한 어노테이션에 의한 MethodArgumentNotValidException을 처리하는 메서드
     * spring framework에 의해 발생하는 Exception
     * @param e
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<Map<String, Object>> validateRes = new ArrayList<>();

        for (FieldError fieldError: fieldErrors) {
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

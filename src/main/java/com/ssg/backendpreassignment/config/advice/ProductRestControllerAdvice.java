package com.ssg.backendpreassignment.config.advice;


import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.controller.ProductRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackageClasses=ProductRestController.class)
@Slf4j
public class ProductRestControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestResponse> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e, HttpServletRequest request) {

        return new ResponseEntity(RestResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .code(HttpStatus.BAD_REQUEST.value())
                .result("업체 명과 상품 명에는 올바른 문자열을, 상품 가격이나 수량에는 숫자 값을 넣어주세요.")
                .build(), HttpStatus.BAD_REQUEST);

    }
}

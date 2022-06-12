package com.ssg.backendpreassignment.config.advice;


import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.controller.ProductRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * ProductRestController에서 HTTP request를 처리할 때 발생하는 Exception들을 처리하는 ExceptionHandler 메서드들을 모아놓은 클래스
 */
@RestControllerAdvice(basePackageClasses=ProductRestController.class)
@RequiredArgsConstructor
public class ProductRestControllerAdvice {

    /**
     * 올바르지 않은 자료형을 request 필드에 사용한 경우 발생하는 HttpMessageNotReadableException 핸들링
     *
     * @param e
     * @param request
     * @return ResponseEntity
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e, HttpServletRequest request) {
        return new ResponseEntity(RestResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .result("잘못 입력된 값이 있습니다. 업체 명과 상품 명에는 올바른 문자열을, 상품 가격이나 수량에는 숫자 값을 넣어주세요.")
                .build(), HttpStatus.BAD_REQUEST);
    }
}

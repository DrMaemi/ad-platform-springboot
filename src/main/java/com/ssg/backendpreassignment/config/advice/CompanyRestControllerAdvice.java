package com.ssg.backendpreassignment.config.advice;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.controller.CompanyRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackageClasses=CompanyRestController.class)
@RequiredArgsConstructor
public class CompanyRestControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e, HttpServletRequest request) {
        return new ResponseEntity(RestResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .result("잘못 입력된 값이 있습니다. 업체명(companyName), 사업자번호(businessRegistrationNumber), 휴대폰번호(phoneNumber), 주소(address) 값에 각각 문자열을 입력해주세요.")
                .build(), HttpStatus.BAD_REQUEST);
    }
}

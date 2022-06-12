package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.dto.CompanyDto;
import com.ssg.backendpreassignment.dto.ProductReqDto;
import com.ssg.backendpreassignment.dto.ProductDto;
import com.ssg.backendpreassignment.service.CompanyService;
import com.ssg.backendpreassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 상품 도메인 관련 HTTP request 매핑 및 처리를 위한 컨트롤러 클래스
 */
@Validated
@RestController
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;
    private final CompanyService companyService;

    /**
     * 생성된 상품 정보 리스트 조회 요청 API
     * 생성된 상품 정보 리스트 반환
     * @return ResponseEntity
     */
    @GetMapping("/api/products")
    public ResponseEntity<?> getProducts() {
        List<Map<String, Object>> resList = new ArrayList<>();

        for (ProductDto productDto: productService.getProducts()) {
            Map<String, Object> eachResMap = new HashMap<>();

            eachResMap.put("id", productDto.getId());
            eachResMap.put("companyName", productDto.getCompanyDto().getName());
            eachResMap.put("productName", productDto.getProductName());
            eachResMap.put("price", productDto.getPrice());
            eachResMap.put("stock", productDto.getStock());
            eachResMap.put("createdDate", productDto.getCreatedDate());
            eachResMap.put("lastModifiedDate", productDto.getLastModifiedDate());

            resList.add(eachResMap);
        }

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .result(resList)
                .build(), HttpStatus.OK);
    }

    /**
     * 1개 이상의 상품 정보 등록 요청 API
     * 요청 데이터를 받아 상품 정보 생성, 생성된 상품 정보 갯수 반환
     * @param productReqDtos
     * @return ResponseEntity
     */
    // 상품 정보 등록 (N개 레코드)
    @PostMapping("/api/products")
    public ResponseEntity<?> addProducts(@RequestBody List<@Valid ProductReqDto> productReqDtos) {
        for (ProductReqDto productReqDto : productReqDtos) {
            CompanyDto companyDto = companyService.findOrCreateByCompanyName(productReqDto.getCompanyName());
            companyService.addProduct(companyDto.getId(), productReqDto.toProductDto());
        }

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .result(String.format("%d record(s) created", productReqDtos.size()))
                .build(), HttpStatus.CREATED);
    }

    /**
     * 1개의 상품 정보 등록 요청 API
     * @param productReqDto
     * @return ResponseEntity
     */
    // 상품 정보 등록 (1개 레코드)
    @PostMapping("/api/product")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductReqDto productReqDto) {
        CompanyDto companyDto = companyService.findOrCreateByCompanyName(productReqDto.getCompanyName());
        companyService.addProduct(companyDto.getId(), productReqDto.toProductDto());

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .build(), HttpStatus.CREATED);
    }
}

package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.dto.CompanyDto;
import com.ssg.backendpreassignment.dto.ProductAddReqDto;
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

@Validated
@RestController
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;
    private final CompanyService companyService;

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

    // 상품 정보 등록 (N개 레코드)
    @PostMapping("/api/products")
    public ResponseEntity<?> addProducts(@RequestBody List<@Valid ProductAddReqDto> productAddReqDtos, Errors errors) {
        for (ProductAddReqDto productAddReqDto: productAddReqDtos) {
            CompanyDto companyDto = companyService.findOrCreateByCompanyName(productAddReqDto.getCompanyName());
            companyService.addProduct(companyDto.getId(), productAddReqDto.toProductDto());
        }

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .result(String.format("%d record(s) created", productAddReqDtos.size()))
                .build(), HttpStatus.CREATED);
    }

    // 상품 정보 등록 (1개 레코드)
    @PostMapping("/api/product")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductAddReqDto productAddReqDto, BindingResult bindingResult, Errors errors) {
        CompanyDto companyDto = companyService.findOrCreateByCompanyName(productAddReqDto.getCompanyName());
        companyService.addProduct(companyDto.getId(), productAddReqDto.toProductDto());

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .build(), HttpStatus.CREATED);
    }
}

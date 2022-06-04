package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.config.validator.DtoListValidator;
import com.ssg.backendpreassignment.dto.CompanyDto;
import com.ssg.backendpreassignment.dto.ProductDto;
import com.ssg.backendpreassignment.service.CompanyService;
import com.ssg.backendpreassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;
    private final CompanyService companyService;
    private final DtoListValidator dtoListValidator;

    @GetMapping("/api/products")
    public ResponseEntity<?> getProducts() {
        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .result(productService.getProducts())
                .build(), HttpStatus.OK);
    }

    // 상품 정보 등록 (N개 레코드)
    @PostMapping("/api/products")
    public ResponseEntity<?> addProducts(@RequestBody List<ProductDto> productDtos, Errors errors) {
        dtoListValidator.validate(productDtos, errors);

        if (errors.hasErrors()) {
            Map<String, String> validatorResult = this.validateHandling(errors);

            return new ResponseEntity<RestResponse>(RestResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .code(HttpStatus.BAD_REQUEST.value())
                    .result(validatorResult)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        for (ProductDto productDto: productDtos) {
            String companyName = productDto.getCompanyDto().getName();
            CompanyDto companyDto = companyService.findOrCreateByCompanyName(companyName);
            companyService.addProduct(companyDto.getId(), productDto);
        }

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .build(), HttpStatus.OK);

    }

    // 상품 정보 등록 (1개 레코드)
    @PostMapping("/api/product")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult, Errors errors) {
        if (errors.hasErrors()) {
            Map<String, String> validatorResult = this.validateHandling(errors);

            return new ResponseEntity<RestResponse>(RestResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .status(HttpStatus.BAD_REQUEST)
                    .result(validatorResult)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        String companyName = productDto.getCompanyDto().getName();
        CompanyDto companyDto = companyService.findOrCreateByCompanyName(companyName);
        companyService.addProduct(companyDto.getId(), productDto);

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build(), HttpStatus.OK);
    }

    @DeleteMapping("/api/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build(), HttpStatus.OK);
    }

    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error: errors.getFieldErrors()) {
            String validKeyName = String.format("not_valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }
}

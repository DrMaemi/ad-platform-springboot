package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.config.validator.CustomValidator;
import com.ssg.backendpreassignment.dto.ProductDto;
import com.ssg.backendpreassignment.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;
    private final CustomValidator customValidator;

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @PostMapping("/api/products")
    public ResponseEntity<?> addProducts(@RequestBody List<ProductDto> productDtos, Errors errors) {
        customValidator.validate(productDtos, errors);

        if (errors.hasErrors()) {
            Map<String, String> validatorResult = this.validateHandling(errors);

            return new ResponseEntity<RestResponse>(RestResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .result(validatorResult)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        List<ProductDto> resDtos = productService.addProducts(productDtos);

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .status(HttpStatus.OK)
                .result(resDtos)
                .build(), HttpStatus.OK);

    }

    @PostMapping("/api/product")
    public ResponseEntity<?> addProduct(@RequestBody @Validated ProductDto productDto, Errors errors) {
        if (errors.hasErrors()) {
            Map<String, String> validatorResult = this.validateHandling(errors);

            return new ResponseEntity<RestResponse>(RestResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .result(validatorResult)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        ProductDto resDto = productService.addProduct(productDto);

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .status(HttpStatus.OK)
                .result(resDto)
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

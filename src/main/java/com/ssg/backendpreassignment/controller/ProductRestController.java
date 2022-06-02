package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.config.validator.CustomValidator;
import com.ssg.backendpreassignment.dto.ProductDto;
import com.ssg.backendpreassignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.hibernate.TypeMismatchException;
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
                    .code(HttpStatus.BAD_REQUEST.value())
                    .result(validatorResult)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        List<ProductDto> resDtos = productService.addProducts(productDtos);

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .result(resDtos)
                .build(), HttpStatus.OK);

    }

    @PostMapping("/api/product")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult, Errors errors) {
        System.out.println("in ProductRestController.addProduct(),");
        System.out.println("productDto: "+productDto);
        System.out.println("bindingResult: "+bindingResult);
        System.out.println("errors: "+errors);

        if (errors.hasErrors()) {
            Map<String, String> validatorResult = this.validateHandling(errors);

            return new ResponseEntity<RestResponse>(RestResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .status(HttpStatus.BAD_REQUEST)
                    .result(validatorResult)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        ProductDto resDto = productService.addProduct(productDto);

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
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

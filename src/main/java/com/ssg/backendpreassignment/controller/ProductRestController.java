package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.dto.ProductDto;
import com.ssg.backendpreassignment.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductRestController {
    private ProductService productService;

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @PostMapping("/api/products")
    public RestResponse<?> addProducts(@RequestBody List<ProductDto> productDtos) {
        System.out.println(productDtos);
        List<ProductDto> resDtos = productService.addProducts(productDtos);

        return RestResponse.builder()
                .status(HttpStatus.OK)
                .result(resDtos)
                .build();
    }

    @PostMapping("/api/product")
    public RestResponse<?> addProduct(@RequestBody ProductDto productDto) {
        ProductDto resDto = productService.addProduct(productDto);

        return RestResponse.builder()
                .status(HttpStatus.OK)
                .result(resDto)
                .build();
    }
}

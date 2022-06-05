package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.entity.ProductEntity;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private CompanyDto companyDto;

    @NotBlank(message="상품명이 비어있습니다.")
    private String productName;

    @NotNull(message="상품 가격이 비어있습니다.")
    @PositiveOrZero(message="상품 가격은 0 이상의 값으로 입력해주세요.")
    private Long price;

    @NotNull(message="상품 수량이 비어있습니다.")
    @PositiveOrZero(message="상품 수량은 0 이상의 값으로 입력해주세요.")
    private Long stock;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public ProductDto(Long id, CompanyDto companyDto, String productName, Long price, Long stock, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.companyDto = companyDto;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .id(this.getId())
                .companyEntity(this.getCompanyDto().toEntity())
                .productName(this.getProductName())
                .price(this.getPrice())
                .stock(this.getStock())
                .build();
    }

    public ProductEntity toEntityExceptCompany() {
        return ProductEntity.builder()
                .id(this.getId())
                .productName(this.getProductName())
                .price(this.getPrice())
                .stock(this.getStock())
                .build();
    }
}

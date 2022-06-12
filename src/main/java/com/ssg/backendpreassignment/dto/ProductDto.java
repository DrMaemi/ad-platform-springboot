package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.entity.ProductEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * ProductEntity의 DTO 클래스
 * ProductRestController ↔ ProductService 레이어 간 전송 데이터 객체
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private CompanyDto companyDto;
    private String productName;
    private Long price;
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

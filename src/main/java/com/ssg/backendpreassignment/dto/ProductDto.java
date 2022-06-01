package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.entity.ProductEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String companyName;
    private String productName;
    private Long price;
    private Long stock;

    @Builder
    public ProductDto(Long id, String companyName, String productName, Long price, Long stock) {
        this.id = id;
        this.companyName = companyName;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .id(this.getId())
                .companyName(this.getCompanyName())
                .productName(this.getProductName())
                .price(this.getPrice())
                .stock(this.getStock())
                .build();
    }
}

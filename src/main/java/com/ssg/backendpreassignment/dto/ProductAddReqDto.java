package com.ssg.backendpreassignment.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductAddReqDto {
    @NotBlank(message="업체명을 입력해주세요.")
    private String companyName;

    @NotBlank(message="상품명을 입력해주세요.")
    private String productName;

    @NotNull(message="상품 가격을 입력해주세요.")
    @PositiveOrZero(message="상품 가격은 0 이상의 값으로 입력해주세요.")
    private Long price;

    @NotNull(message="상품 수량을 입력해주세요.")
    @PositiveOrZero(message="상품 수량은 0 이상의 값으로 입력해주세요.")
    private Long stock;

    @Builder
    public ProductAddReqDto(String companyName, String productName, Long price, Long stock) {
        this.companyName = companyName;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public ProductDto toProductDto() {
        return ProductDto.builder()
                .companyDto(CompanyDto.builder()
                        .name(this.getCompanyName())
                        .build())
                .productName(this.getProductName())
                .price(this.getPrice())
                .stock(this.getStock())
                .build();
    }
}

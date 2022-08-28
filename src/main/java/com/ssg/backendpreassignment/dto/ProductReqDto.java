package com.ssg.backendpreassignment.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * 클라이언트가 상품 도메인 관련 HTTP 요청 시 전송하는 DTO 클래스
 * Client ↔ ProductRestController 레이어 간 전송 데이터 객체
 */
@Getter
@ToString
@NoArgsConstructor
public class ProductReqDto {
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
    public ProductReqDto(String companyName, String productName, Long price, Long stock) {
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

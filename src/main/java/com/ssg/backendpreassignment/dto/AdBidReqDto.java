package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.config.validator.CompanyOwnsProduct;
import com.ssg.backendpreassignment.config.validator.InContract;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 클라이언트가 광고입찰 도메인 관련 HTTP 요청 시 전송하는 DTO 클래스
 * Client ↔ AdBidRestController 레이어 간 전송 데이터 객체
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@CompanyOwnsProduct
public class AdBidReqDto {
    @NotNull(message="업체 ID를 입력해주세요.")
    @Min(value=(long)1e9+1, message="업체 ID는 최소 10자리로 입력해주세요.")
    @InContract
    private Long companyId;

    @NotNull(message="상품 ID를 입력해주세요.")
    @Min(value=(long)1e9+1, message="상품 ID는 최소 10자리로 입력해주세요.")
    private Long productId;

    @NotNull(message="광고입찰가를 입력해주세요.")
    @Max(value=(long)1e6, message="광고입찰가는 최대 백만(1,000,000)원까지 입력할 수 있습니다.")
    private Long bidPrice;

    @Builder
    public AdBidReqDto(Long companyId, Long productId, Long bidPrice) {
        this.companyId = companyId;
        this.productId = productId;
        this.bidPrice = bidPrice;
    }
}

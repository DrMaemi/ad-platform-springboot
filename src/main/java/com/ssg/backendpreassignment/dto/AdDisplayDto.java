package com.ssg.backendpreassignment.dto;

import lombok.*;

/**
 * AdDisplayEntity의 DTO 클래스
 * AdDisplayRestController ↔ AdDisplayService 레이어 간 전송 데이터 객체
 */
@Getter
@ToString
@NoArgsConstructor
public class AdDisplayDto {
    private Long bidId;
    private Long companyId;
    private Long productId;
    private Long bidPrice;

    @Builder
    public AdDisplayDto(Long bidId, Long companyId, Long productId, Long bidPrice) {
        this.bidId = bidId;
        this.companyId = companyId;
        this.productId = productId;
        this.bidPrice = bidPrice;
    }
}

package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.config.validator.InBid;
import lombok.*;

/**
 * 클라이언트가 광고과금 도메인 관련 HTTP 요청 시 전송하는 DTO 클래스
 * Client ↔ AdChargeRestController 레이어 간 전송 데이터 객체
 */
@Getter
@ToString
@NoArgsConstructor
public class AdChargeReqDto {
    @InBid
    private Long bidId;

    @Builder
    public AdChargeReqDto(Long bidId) {
        this.bidId = bidId;
    }
}

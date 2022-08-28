package com.ssg.backendpreassignment.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * AdChargeEntity의 DTO 클래스
 * AdChargeRestController ↔ AdChargeService 레이어 간 전송 데이터 객체
 */
@Getter
@ToString
@NoArgsConstructor
public class AdChargeDto {
    private Long id;
    private AdBidDto adBidDto;
    private Long bidPrice;
    private LocalDateTime clickedDate;

    @Builder
    public AdChargeDto(Long id, AdBidDto adBidDto, Long bidPrice, LocalDateTime clickedDate) {
        this.id = id;
        this.adBidDto = adBidDto;
        this.bidPrice = bidPrice;
        this.clickedDate = clickedDate;
    }
}

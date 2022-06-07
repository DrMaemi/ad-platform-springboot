package com.ssg.backendpreassignment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdvertisementChargeDto {
    private Long id;
    private AdvertisementBidDto advertisementBidDto;
    private Long bidPrice;
    private LocalDateTime clickedDate;

    @Builder
    public AdvertisementChargeDto(Long id, AdvertisementBidDto advertisementBidDto, Long bidPrice, LocalDateTime clickedDate) {
        this.id = id;
        this.advertisementBidDto = advertisementBidDto;
        this.bidPrice = bidPrice;
        this.clickedDate = clickedDate;
    }
}

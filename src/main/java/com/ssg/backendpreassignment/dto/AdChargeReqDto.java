package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.config.validator.InBid;
import lombok.*;

@Getter
@Setter
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

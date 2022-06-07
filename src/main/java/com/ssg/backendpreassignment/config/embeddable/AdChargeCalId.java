package com.ssg.backendpreassignment.config.embeddable;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Embeddable
public class AdChargeCalId implements Serializable {
    @Column(name="clicked_date")
    private LocalDate clickedDate;

    @Column(name="bid_id")
    private Long bidId;

    @Builder
    public AdChargeCalId(LocalDate clickedDate, Long bidId) {
        this.clickedDate = clickedDate;
        this.bidId = bidId;
    }
}

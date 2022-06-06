package com.ssg.backendpreassignment.entity;

import com.ssg.backendpreassignment.dto.AdvertisementDisplayDto;
import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Immutable
@Entity
@Table(name="ADVERTISEMENT_DISPLAY")
public class AdvertisementDisplayEntity implements Serializable {
    @Id
    private Long bidId;
    private Long companyId;
    private Long productId;
    private Long bidPrice;

    public AdvertisementDisplayDto toDto() {
        return AdvertisementDisplayDto.builder()
                .bidId(this.getBidId())
                .companyId(this.getCompanyId())
                .productId(this.getProductId())
                .bidPrice(this.getBidPrice())
                .build();
    }
}

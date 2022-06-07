package com.ssg.backendpreassignment.entity;

import com.ssg.backendpreassignment.dto.AdDisplayDto;
import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Immutable
@Entity
@Table(name="ADVERTISEMENT_DISPLAY")
public class AdDisplayEntity implements Serializable {
    @Id
    private Long bidId;
    private Long companyId;
    private Long productId;
    private Long bidPrice;

    public AdDisplayDto toDto() {
        return AdDisplayDto.builder()
                .bidId(this.getBidId())
                .companyId(this.getCompanyId())
                .productId(this.getProductId())
                .bidPrice(this.getBidPrice())
                .build();
    }
}

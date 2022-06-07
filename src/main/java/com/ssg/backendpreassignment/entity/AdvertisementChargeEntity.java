package com.ssg.backendpreassignment.entity;

import com.ssg.backendpreassignment.dto.AdvertisementChargeDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access=AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="ADVERTISEMENT_CHARGE")
public class AdvertisementChargeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bid_id")
    private AdvertisementBidEntity advertisementBidEntity;

    @Column
    private Long bidPrice;

    @CreatedDate
    @Column(updatable=false, nullable=false)
    private LocalDateTime clickedDate;

    @Builder
    public AdvertisementChargeEntity(Long id, AdvertisementBidEntity advertisementBidEntity, Long bidPrice) {
        this.id = id;
        this.advertisementBidEntity = advertisementBidEntity;
        this.bidPrice = bidPrice;
    }

    public AdvertisementChargeDto toDto() {
        return AdvertisementChargeDto.builder()
                .id(this.getId())
                .advertisementBidDto(this.getAdvertisementBidEntity().toDto())
                .bidPrice(this.getBidPrice())
                .build();
    }
}

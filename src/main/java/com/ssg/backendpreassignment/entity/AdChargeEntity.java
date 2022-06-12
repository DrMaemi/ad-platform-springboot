package com.ssg.backendpreassignment.entity;

import com.ssg.backendpreassignment.dto.AdChargeDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * DB 테이블 'ADVERTISEMENT_CHARGE'에 매핑되는 엔티티 클래스
 * AdBidEntity와 다대일 단방향 연관 관계
 */
@Getter
@Setter
@NoArgsConstructor(access=AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="ADVERTISEMENT_CHARGE")
public class AdChargeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bid_id")
    private AdBidEntity adBidEntity;

    @Column
    private Long bidPrice;

    @CreatedDate
    @Column(updatable=false, nullable=false)
    private LocalDateTime clickedDate;

    @Builder
    public AdChargeEntity(Long id, AdBidEntity adBidEntity, Long bidPrice) {
        this.id = id;
        this.adBidEntity = adBidEntity;
        this.bidPrice = bidPrice;
    }

    public AdChargeDto toDto() {
        return AdChargeDto.builder()
                .id(this.getId())
                .adBidDto(this.getAdBidEntity().toDtoExceptAll())
                .bidPrice(this.getBidPrice())
                .build();
    }
}

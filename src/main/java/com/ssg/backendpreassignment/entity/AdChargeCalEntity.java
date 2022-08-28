package com.ssg.backendpreassignment.entity;

import com.ssg.backendpreassignment.config.embeddable.AdChargeCalId;
import com.ssg.backendpreassignment.dto.AdChargeCalDto;
import lombok.*;

import javax.persistence.*;

/**
 * DB 테이블 'ADVERTISEMENT_CHARGE_CAL'에 매핑되는 엔티티 클래스
 * 클릭일자, 광고입찰 ID 두 개 컬럼을 복합키로 가짐
 */
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name="ADVERTISEMENT_CHARGE_CAL")
public class AdChargeCalEntity {
    @EmbeddedId
    private AdChargeCalId adChargeCalId;

    @Column
    private Long companyId;

    @Column
    private String companyName;

    @Column
    private Long productId;

    @Column
    private String productName;

    @Column
    private Long cntClicked;

    @Column
    private Long totalCharge;

    @Builder
    public AdChargeCalEntity(AdChargeCalId adChargeCalId, Long companyId, String companyName, Long productId, String productName, Long cntClicked, Long totalCharge) {
        this.adChargeCalId = adChargeCalId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.productId = productId;
        this.productName = productName;
        this.cntClicked = cntClicked;
        this.totalCharge = totalCharge;
    }

    public AdChargeCalDto toDto() {
        return AdChargeCalDto.builder()
                .adChargeCalId(this.getAdChargeCalId())
                .companyId(this.getCompanyId())
                .companyName(this.getCompanyName())
                .productId(this.getProductId())
                .productName(this.getProductName())
                .cntClicked(this.getCntClicked())
                .totalCharge(this.getTotalCharge())
                .build();
    }
}

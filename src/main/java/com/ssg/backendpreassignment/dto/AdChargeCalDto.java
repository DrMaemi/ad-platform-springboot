package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.config.embeddable.AdChargeCalId;
import com.ssg.backendpreassignment.entity.AdChargeCalEntity;
import lombok.*;

/**
 * AdChargeCalEntity의 DTO 클래스
 * AdChargeRestController ↔ AdChargeCalService 레이어 간 전송 데이터 객체
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdChargeCalDto {
    private AdChargeCalId adChargeCalId;
    private Long companyId;
    private String companyName;
    private Long productId;
    private String productName;
    private Long cntClicked;
    private Long totalCharge;

    @Builder
    public AdChargeCalDto(AdChargeCalId adChargeCalId, Long companyId, String companyName, Long productId, String productName, Long cntClicked, Long totalCharge) {
        this.adChargeCalId = adChargeCalId;
        this.companyId = companyId;
        this.companyName = companyName;
        this.productId = productId;
        this.productName = productName;
        this.cntClicked = cntClicked;
        this.totalCharge = totalCharge;
    }

    public AdChargeCalEntity toEntity() {
        return AdChargeCalEntity.builder()
                .adChargeCalId(this.getAdChargeCalId())
                .companyId(this.getCompanyId())
                .companyName(this.getCompanyName())
                .productId(this.productId)
                .productName(this.getProductName())
                .cntClicked(this.getCntClicked())
                .totalCharge(this.getTotalCharge())
                .build();
    }
}

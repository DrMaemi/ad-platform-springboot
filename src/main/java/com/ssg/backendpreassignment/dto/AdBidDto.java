package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.entity.AdBidEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * AdBidEntity의 DTO 클래스
 * AdBidRestController ↔ AdBidService 레이어 간 전송 데이터 객체
 */
@Getter
@ToString
@NoArgsConstructor
public class AdBidDto {
    private Long id;
    private ContractDto contractDto;
    private ProductDto productDto;
    private Long bidPrice;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public AdBidDto(Long id, ContractDto contractDto, ProductDto productDto, Long bidPrice, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.contractDto = contractDto;
        this.productDto = productDto;
        this.bidPrice = bidPrice;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public AdBidEntity toEntity() {
        return AdBidEntity.builder()
                .id(this.getId())
                .contractEntity(this.getContractDto().toEntityExceptCompany())
                .productEntity(this.getProductDto().toEntityExceptCompany())
                .bidPrice(this.getBidPrice())
                .build();
    }
}

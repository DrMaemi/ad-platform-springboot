package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.entity.AdvertisementBidEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdvertisementBidDto {
    private Long id;
    private ContractDto contractDto;
    private ProductDto productDto;
    private Long bidPrice;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public AdvertisementBidDto(Long id, ContractDto contractDto, ProductDto productDto, Long bidPrice, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.contractDto = contractDto;
        this.productDto = productDto;
        this.bidPrice = bidPrice;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public AdvertisementBidEntity toEntity() {
        return AdvertisementBidEntity.builder()
                .id(this.getId())
                .contractEntity(this.getContractDto().toEntityExceptCompany())
                .productEntity(this.getProductDto().toEntityExceptCompany())
                .bidPrice(this.getBidPrice())
                .build();
    }
}

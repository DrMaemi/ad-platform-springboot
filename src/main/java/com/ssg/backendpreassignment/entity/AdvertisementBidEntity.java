package com.ssg.backendpreassignment.entity;

import com.ssg.backendpreassignment.dto.AdvertisementBidDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name="ADVERTISEMENT_BID")
public class AdvertisementBidEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="company_id", referencedColumnName="company_id")
    private ContractEntity contractEntity;

    @OneToOne
    @JoinColumn(name="product_id")
    private ProductEntity productEntity;

    @Column
    private Long bidPrice;

    @Builder
    public AdvertisementBidEntity(Long id, ContractEntity contractEntity, ProductEntity productEntity, Long bidPrice) {
        this.id = id;
        this.contractEntity = contractEntity;
        this.productEntity = productEntity;
        this.bidPrice = bidPrice;
    }

    public AdvertisementBidDto toDto() {
        return AdvertisementBidDto.builder()
                .id(this.getId())
                .contractDto(this.getContractEntity().toDto())
                .productDto(this.getProductEntity().toDtoExceptCompany())
                .bidPrice(this.getBidPrice())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }
}

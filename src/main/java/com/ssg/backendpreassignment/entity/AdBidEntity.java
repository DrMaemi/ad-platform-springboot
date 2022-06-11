package com.ssg.backendpreassignment.entity;

import com.ssg.backendpreassignment.dto.AdBidDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name="ADVERTISEMENT_BID")
public class AdBidEntity extends TimeEntity {
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
    public AdBidEntity(Long id, ContractEntity contractEntity, ProductEntity productEntity, Long bidPrice) {
        this.id = id;
        this.contractEntity = contractEntity;
        this.productEntity = productEntity;
        this.bidPrice = bidPrice;
    }

    public AdBidDto toDto() {
        return AdBidDto.builder()
                .id(this.getId())
                .contractDto(this.getContractEntity().toDto())
                .productDto(this.getProductEntity().toDtoExceptCompany())
                .bidPrice(this.getBidPrice())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }

    public AdBidDto toDtoExceptAll() {
        return AdBidDto.builder()
                .id(this.getId())
                .bidPrice(this.getBidPrice())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }
}

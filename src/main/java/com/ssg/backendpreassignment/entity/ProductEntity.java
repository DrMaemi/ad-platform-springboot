package com.ssg.backendpreassignment.entity;

import com.ssg.backendpreassignment.dto.ProductDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Entity
@Table(name="PRODUCT")
public class ProductEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="company_name", referencedColumnName="name")
    private CompanyEntity companyEntity;

    @Column(nullable=false)
    private String productName;

    @Column(nullable=false)
    private Long price;

    @Column
    private Long stock;

    @Builder
    public ProductEntity(Long id, CompanyEntity companyEntity, String productName, Long price, Long stock) {
        this.id = id;
        this.companyEntity = companyEntity;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public ProductDto toDto() {
        return ProductDto.builder()
                .id(this.getId())
                .companyDto(this.getCompanyEntity().toDtoExceptProducts())
                .productName(this.getProductName())
                .price(this.getPrice())
                .stock(this.getStock())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }

    public ProductDto toDtoExceptCompany() {
        return ProductDto.builder()
                .id(this.getId())
                .productName(this.getProductName())
                .price(this.getPrice())
                .stock(this.getStock())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }
}

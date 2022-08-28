package com.ssg.backendpreassignment.entity;

import com.ssg.backendpreassignment.dto.ProductDto;
import lombok.*;

import javax.persistence.*;

/**
 * DB 테이블 'PRODUCT'에 매핑되는 엔티티 클래스
 * CompanyEntity와 다대일 양방향 연관 관계
 */
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Entity
@Table(name="PRODUCT")
public class ProductEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_name", referencedColumnName="name")
    private CompanyEntity companyEntity;

    @Column(nullable=false)
    private String productName;

    @Column(nullable=false)
    private Long price;

    @Column(nullable=false)
    private Long stock;

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

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

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

    @Column(nullable=false, unique=true)
    private String companyName;

    @Column(nullable=false)
    private String productName;

    @Column(nullable=false)
    private Long price;

    @Column
    private Long stock;

    @Builder
    public ProductEntity(Long id, String companyName, String productName, Long price, Long stock) {
        this.id = id;
        this.companyName = companyName;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public ProductDto toDto() {
        return ProductDto.builder()
                .id(this.getId())
                .companyName(this.getCompanyName())
                .productName(this.getProductName())
                .price(this.getPrice())
                .stock(this.getStock())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }
}

package com.ssg.backendpreassignment.entity;

import com.ssg.backendpreassignment.dto.CompanyDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DB 테이블 'COMPANY'에 매핑되는 엔티티 클래스
 * ProductEntity와 일대다 양방향 연관 관계
 */
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Entity
@Table(name="COMPANY")
public class CompanyEntity extends TimeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length=30, unique=true, nullable=false, updatable=false)
    private String name;

    @Setter
    @Column(length=10)
    private String businessRegistrationNumber;

    @Setter
    @Column(length=20)
    private String phoneNumber;

    @Setter
    @Column(length=50)
    private String address;

    @OneToMany(mappedBy="companyEntity", fetch=FetchType.LAZY)
    private List<ProductEntity> productEntities;

    @Builder
    public CompanyEntity(Long id, String name, String businessRegistrationNumber, String phoneNumber, String address, List<ProductEntity> productEntities) {
        this.id = id;
        this.name = name;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.productEntities = productEntities;
    }

    public CompanyDto toDto() {
        return CompanyDto.builder()
                .id(this.getId())
                .name(this.getName())
                .businessRegistrationNumber(this.getBusinessRegistrationNumber())
                .phoneNumber(this.getPhoneNumber())
                .address(this.getAddress())
                .productDtos(this.getProductEntities().stream().map(productEntity -> productEntity.toDtoExceptCompany()).collect(Collectors.toList()))
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }

    public CompanyDto toDtoExceptProducts() {
        return CompanyDto.builder()
                .id(this.getId())
                .name(this.getName())
                .businessRegistrationNumber(this.getBusinessRegistrationNumber())
                .phoneNumber(this.getPhoneNumber())
                .address(this.getAddress())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }

    public void addProduct(final ProductEntity productEntity) {
        if (this.getProductEntities() == null) {
            this.productEntities = new ArrayList<>();
        }
        this.productEntities.add(productEntity);
    }
}

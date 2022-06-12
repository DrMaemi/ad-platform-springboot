package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.entity.CompanyEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CompanyEntity의 DTO 클래스
 * CompanyRestController ↔ CompanyService 레이어 간 전송 데이터 객체
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CompanyDto {
    private Long id;

    @NotBlank(message="업체명이 비어있습니다.")
    private String name;

    @NotBlank(message="사업자 번호란이 비어있습니다.")
    private String businessRegistrationNumber;

    @NotBlank(message="휴대폰 번호란이 비어있습니다.")
    private String phoneNumber;

    private String address;
    private List<ProductDto> productDtos;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public CompanyDto(Long id, String name, String businessRegistrationNumber, String phoneNumber, String address, List<ProductDto> productDtos, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.productDtos = productDtos;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public CompanyEntity toEntity() {
        if (this.getProductDtos() == null) {
            this.setProductDtos(new ArrayList<>());
        }

        return CompanyEntity.builder()
                .id(this.getId())
                .name(this.getName())
                .businessRegistrationNumber(this.getBusinessRegistrationNumber())
                .phoneNumber(this.getPhoneNumber())
                .address(this.getAddress())
                .productEntities(this.getProductDtos().stream().map(productDto -> productDto.toEntity()).collect(Collectors.toList()))
                .build();
    }
}

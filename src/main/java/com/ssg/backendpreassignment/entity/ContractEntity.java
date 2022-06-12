package com.ssg.backendpreassignment.entity;

import com.ssg.backendpreassignment.dto.ContractDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DB 테이블 'CONTRACT'에 매핑되는 엔티티 클래스
 * CompanyEntity와 다대일 단방향 연관 관계
 */
@Getter
@Setter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name="CONTRACT")
public class ContractEntity extends TimeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_id")
    private CompanyEntity companyEntity;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Builder
    public ContractEntity(Long id, CompanyEntity companyEntity, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.companyEntity = companyEntity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ContractDto toDto() {
        return ContractDto.builder()
                .id(this.getId())
                .companyDto(this.getCompanyEntity().toDtoExceptProducts())
                .startDate(this.getStartDate())
                .endDate(this.getEndDate())
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .build();
    }
}

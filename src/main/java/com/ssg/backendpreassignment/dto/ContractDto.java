package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.entity.ContractEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ContractDto {
    private Long id;
    private CompanyDto companyDto;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    public ContractDto(Long id, CompanyDto companyDto, LocalDate startDate, LocalDate endDate, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.companyDto = companyDto;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public ContractEntity toEntity() {
        return ContractEntity.builder()
                .id(this.getId())
                .companyEntity(this.getCompanyDto().toEntity())
                .startDate(this.getStartDate())
                .endDate(this.getEndDate())
                .build();
    }

    public ContractEntity toEntityExceptCompany() {
        return ContractEntity.builder()
                .id(this.getId())
                .startDate(this.getStartDate())
                .endDate(this.getEndDate())
                .build();
    }
}

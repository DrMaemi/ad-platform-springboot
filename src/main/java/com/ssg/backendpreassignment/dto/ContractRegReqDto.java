package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.config.validator.ContractNotOverlapped;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ContractRegReqDto {
    @NotNull(message="업체 ID를 입력해주세요.")
    @Min(value=(long)1e9+1, message="업체 ID는 최소 10자리로 입력해주세요.")
    @ContractNotOverlapped
    private Long companyId;

    @Builder
    public ContractRegReqDto(Long companyId) {
        this.companyId = companyId;
    }
}
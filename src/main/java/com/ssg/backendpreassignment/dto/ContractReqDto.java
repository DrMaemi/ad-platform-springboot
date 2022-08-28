package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.config.validator.ContractNotOverlapped;
import com.ssg.backendpreassignment.config.validator.RegisteredCompany;
import lombok.*;

import javax.validation.constraints.*;

/**
 * 클라이언트가 계약 도메인 관련 HTTP 요청 시 전송하는 DTO 클래스
 * Client ↔ ContractRestController 레이어 간 전송 데이터 객체
 */
@Getter
@ToString
@NoArgsConstructor
public class ContractReqDto {
    @NotNull(message="업체 ID를 입력해주세요.")
    @Min(value=(long)1e9+1, message="업체 ID는 최소 10자리로 입력해주세요.")
    @RegisteredCompany
    @ContractNotOverlapped
    private Long companyId;

    @Builder
    public ContractReqDto(Long companyId) {
        this.companyId = companyId;
    }
}
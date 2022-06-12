package com.ssg.backendpreassignment.dto;

import com.ssg.backendpreassignment.config.validator.*;
import com.ssg.backendpreassignment.config.validator.group.CompanyRegister;
import lombok.*;

import javax.validation.constraints.NotBlank;

/**
 * 클라이언트가 업체 도메인 관련 HTTP 요청 시 전송하는 DTO 클래스
 * Client ↔ CompanyRestController 레이어 간 전송 데이터 객체
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CompanyReqDto {
    @NotBlank(message="업체명을 입력해주세요.")
    @CompanyNameExistInRepo
    private String companyName;

    @NotBlank(message="사업자번호를 입력해주세요.", groups=CompanyRegister.class)
    @BusinessRegNum
    @BusinessRegNumNotDuplicated
    private String businessRegistrationNumber;

    @NotBlank(message="업체전화번호를 입력해주세요.", groups=CompanyRegister.class)
    @PhoneNum
    private String phoneNumber;

    @NotBlank(message="주소지를 입력해주세요.", groups=CompanyRegister.class)
    @NullOrNotBlank(message="주소지를 입력해주세요.")
    private String address;

    @Builder
    public CompanyReqDto(String companyName, String businessRegistrationNumber, String phoneNumber, String address) {
        this.companyName = companyName;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}

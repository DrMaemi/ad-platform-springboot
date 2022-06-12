package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.config.validator.group.CompanyRegister;
import com.ssg.backendpreassignment.dto.CompanyDto;
import com.ssg.backendpreassignment.dto.CompanyReqDto;
import com.ssg.backendpreassignment.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 업체 도메인 관련 HTTP request 매핑 및 처리를 위한 컨트롤러 클래스
 */
@Validated
@RestController
@RequiredArgsConstructor
public class CompanyRestController {
    private final CompanyService companyService;

    /**
     * 업체 리스트 조회 요청 API
     * 생성된 업체 Master 정보 리스트 반환
     * @return ResponseEntity
     */
    @GetMapping("/api/companies")
    public ResponseEntity<?> getCompanies() {
        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(companyService.getCompanies())
                .build(), HttpStatus.OK);
    }

    /**
     * 업체 등록 요청 API
     * 요청 데이터를 받아 DB에 생성, 생성된 데이터 반환
     * @param companyReqDto
     * @return ResponseEntity
     */
    @Validated(CompanyRegister.class)
    @PostMapping("/api/company")
    public ResponseEntity<?> registerCompany(@RequestBody @Valid CompanyReqDto companyReqDto) {
        CompanyDto resDto = companyService.updateCompany(companyReqDto);
        Map<String, Object> resMap = new HashMap<>();

        resMap.put("id", resDto.getId());
        resMap.put("companyName", resDto.getName());
        resMap.put("businessRegistrationNumber", resDto.getBusinessRegistrationNumber());
        resMap.put("phoneNumber", resDto.getPhoneNumber());
        resMap.put("address", resDto.getAddress());

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(resMap)
                .build(), HttpStatus.OK);
    }

    /**
     * 업체 정보 수정 요청 API
     * 요청 데이터를 받아 DB에 반영, 반영된 데이터 반환
     * @param companyReqDto
     * @return ResponseEntity
     */
    @PatchMapping("/api/company")
    public ResponseEntity<?> updateCompany(@RequestBody @Valid CompanyReqDto companyReqDto) {
        CompanyDto resDto = companyService.updateCompany(companyReqDto);
        Map<String, Object> resMap = new HashMap<>();

        resMap.put("id", resDto.getId());
        resMap.put("companyName", resDto.getName());
        resMap.put("businessRegistrationNumber", resDto.getBusinessRegistrationNumber());
        resMap.put("phoneNumber", resDto.getPhoneNumber());
        resMap.put("address", resDto.getAddress());

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(resMap)
                .build(), HttpStatus.OK);
    }
}

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

@Validated
@RestController
@RequiredArgsConstructor
public class CompanyRestController {
    private final CompanyService companyService;

    @GetMapping("/api/companies")
    public ResponseEntity<?> getCompanies() {
        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(companyService.getCompanies())
                .build(), HttpStatus.OK);
    }

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

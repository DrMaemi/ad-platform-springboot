package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.dto.ContractDto;
import com.ssg.backendpreassignment.dto.ContractReqDto;
import com.ssg.backendpreassignment.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 계약 도메인 관련 HTTP request 매핑 및 처리를 위한 컨트롤러 클래스
 */
@RestController
@RequiredArgsConstructor
public class ContractRestController {
    private final ContractService contractService;

    /**
     * 생성된 계약 정보 리스트 조회 요청 API
     * 생성된 계약 정보 리스트 반환
     * @return ResponseEntity
     */
    @GetMapping("/api/contracts")
    public ResponseEntity<?> getContracts() {
        List<Map<String, Object>> resList = new ArrayList<>();

        for (ContractDto contractDto: contractService.getContracts()) {
            Map<String, Object> eachResMap = new HashMap<>();

            eachResMap.put("id", contractDto.getId());
            eachResMap.put("companyId", contractDto.getCompanyDto().getId());
            eachResMap.put("startDate", contractDto.getStartDate());
            eachResMap.put("endDate", contractDto.getEndDate());
            eachResMap.put("createdDate", contractDto.getCreatedDate());
            eachResMap.put("lastModifiedDate", contractDto.getLastModifiedDate());

            resList.add(eachResMap);
        }

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(resList)
                .build(), HttpStatus.OK);
    }

    /**
     * 계약 생성 요청 API
     * 요청 데이터를 받아 DB에 생성, 생성된 데이터 반환
     * @param contractReqDto
     * @return ResponseEntity
     */
    @PostMapping("/api/contract")
    public ResponseEntity<?> createContract(@RequestBody @Valid ContractReqDto contractReqDto) {

        ContractDto resDto = contractService.createContract(contractReqDto);
        Map<String, Object> resMap = new HashMap<>();

        resMap.put("id", resDto.getId());
        resMap.put("companyId", resDto.getCompanyDto().getId());
        resMap.put("startDate", resDto.getStartDate());
        resMap.put("endDate", resDto.getEndDate());

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .result(resMap)
                .build(), HttpStatus.CREATED);
    }
}

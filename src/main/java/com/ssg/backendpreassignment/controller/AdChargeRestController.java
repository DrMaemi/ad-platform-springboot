package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.dto.AdChargeCalDto;
import com.ssg.backendpreassignment.dto.AdChargeDto;
import com.ssg.backendpreassignment.dto.AdChargeReqDto;
import com.ssg.backendpreassignment.service.AdChargeCalService;
import com.ssg.backendpreassignment.service.AdChargeService;
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
 * 광고과금 및 과금 정산 도메인 관련 HTTP request 매핑 및 처리를 위한 컨트롤러 클래스
 */
@RestController
@RequiredArgsConstructor
public class AdChargeRestController {
    private final AdChargeService adChargeService;
    private final AdChargeCalService adChargeCalService;

    /**
     * 광고입찰 ID에 따른 광고과금 데이터 생성 요청 API
     * 요청 데이터를 받아 DB에 생성, 생성된 데이터 반환
     * @param adChargeReqDto
     * @return ResponseEntity
     */
    @PostMapping("/api/ad/charge")
    public ResponseEntity<?> createAdCharge(@RequestBody @Valid AdChargeReqDto adChargeReqDto) {
        AdChargeDto resDto = adChargeService.createAdCharge(adChargeReqDto.getBidId());
        Map<String, Object> resMap = new HashMap<>();

        resMap.put("id", resDto.getId());
        resMap.put("bidId", resDto.getAdBidDto().getId());
        resMap.put("clickedDate", resDto.getClickedDate());
        resMap.put("bidPrice", resDto.getBidPrice());

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .result(resMap)
                .build(), HttpStatus.CREATED);
    }

    /**
     * 광고과금 정산 데이터 리스트 조회 요청 API
     * 광고과금 정산 데이터 리스트 반환
     * @return
     */
    @GetMapping("/api/ad/charge/cals")
    public ResponseEntity<?> getAdChargeCals() {
        List<AdChargeCalDto> resDtos = adChargeCalService.getAdChargeCals();
        List<Map<String, Object>> resList = new ArrayList<>();

        for (AdChargeCalDto resDto: resDtos) {
            Map<String, Object> eachResMap = new HashMap<>();

            eachResMap.put("clickedDate", resDto.getAdChargeCalId().getClickedDate());
            eachResMap.put("bidId", resDto.getAdChargeCalId().getBidId());
            eachResMap.put("companyId", resDto.getCompanyId());
            eachResMap.put("companyName", resDto.getCompanyName());
            eachResMap.put("productId", resDto.getProductId());
            eachResMap.put("productName", resDto.getProductName());
            eachResMap.put("cntClicked", resDto.getCntClicked());
            eachResMap.put("totalCharge", resDto.getTotalCharge());

            resList.add(eachResMap);
        }

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(resList)
                .build(), HttpStatus.OK);
    }
}

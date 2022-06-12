package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.dto.AdChargeDto;
import com.ssg.backendpreassignment.dto.AdChargeReqDto;
import com.ssg.backendpreassignment.service.AdChargeCalService;
import com.ssg.backendpreassignment.service.AdChargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdChargeRestController {
    private final AdChargeService adChargeService;
    private final AdChargeCalService adChargeCalService;

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

    @GetMapping("/api/ad/charge/cals")
    public ResponseEntity<?> getAdChargeCals() {
        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(adChargeCalService.getAdChargeCals())
                .build(), HttpStatus.OK);
    }
}

package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.dto.AdBidDto;
import com.ssg.backendpreassignment.dto.AdBidReqDto;
import com.ssg.backendpreassignment.service.AdBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdBidRestController {
    private final AdBidService adBidService;

    @GetMapping("/api/adbids")
    public ResponseEntity<?> getBids() {
        List<AdBidDto> adBidDtos = adBidService.getAdBids();

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(adBidDtos)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/api/adbid")
    public ResponseEntity<?> createAdBid(@RequestBody @Valid AdBidReqDto adBidReqDto) {
        AdBidDto resDto = adBidService.createAdBid(adBidReqDto);
        Map<String, Object> resMap = new HashMap<>();

        resMap.put("id", resDto.getId());
        resMap.put("companyId", resDto.getContractDto().getCompanyDto().getId());
        resMap.put("productId", resDto.getProductDto().getId());
        resMap.put("bidPrice", resDto.getBidPrice());
        resMap.put("createdDate", resDto.getCreatedDate());
        resMap.put("lastModifiedDate", resDto.getLastModifiedDate());

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED)
                .result(resMap)
                .build(), HttpStatus.CREATED);
    }
}

package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.dto.AdBidDto;
import com.ssg.backendpreassignment.service.AdBidService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdBidRestController {
    private final AdBidService adBidService;

    @GetMapping("/api/adbids")
    public ResponseEntity<?> getBids() {
        List<AdBidDto> adBidDtos = adBidService.getBids();

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(adBidDtos)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/api/adbid")
    public ResponseEntity<?> registerBid(@RequestBody AdBidDto adBidDto) {
        adBidService.registerBid(adBidDto);

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build(), HttpStatus.OK);
    }
}

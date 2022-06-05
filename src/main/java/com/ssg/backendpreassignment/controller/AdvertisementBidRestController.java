package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.dto.AdvertisementBidDto;
import com.ssg.backendpreassignment.service.AdvertisementBidService;
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
public class AdvertisementBidRestController {
    private final AdvertisementBidService advertisementBidService;

    @GetMapping("/api/advertisement_bids")
    public ResponseEntity<?> getBids() {
        List<AdvertisementBidDto> advertisementBidDtos = advertisementBidService.getBids();

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(advertisementBidDtos)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/api/advertisement_bid")
    public ResponseEntity<?> registerBid(@RequestBody AdvertisementBidDto advertisementBidDto) {
        advertisementBidService.registerBid(advertisementBidDto);

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build(), HttpStatus.OK);
    }
}

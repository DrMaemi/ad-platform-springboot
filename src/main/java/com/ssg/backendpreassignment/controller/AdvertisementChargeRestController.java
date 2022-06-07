package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.service.AdvertisementChargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdvertisementChargeRestController {
    private final AdvertisementChargeService advertisementChargeService;

    @PatchMapping("/api/click/ad/{bidId}")
    public ResponseEntity<?> clickAd(@PathVariable Long bidId) {
        advertisementChargeService.createChargeByBidId(bidId);

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build(), HttpStatus.OK);
    }
}

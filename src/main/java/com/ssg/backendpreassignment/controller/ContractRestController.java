package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.dto.ContractDto;
import com.ssg.backendpreassignment.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ContractRestController {
    private final ContractService contractService;

    @GetMapping("/api/contracts")
    public ResponseEntity<?> getContracts() {
        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(contractService.getContracts())
                .build(), HttpStatus.OK);
    }

    @PostMapping("/api/contract")
    public ResponseEntity<?> doContract(@RequestBody ContractDto contractDto) {
        contractDto.setEndDate(contractDto.getStartDate().plusYears(1));
        contractService.doContract(contractDto);

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .build(), HttpStatus.OK);
    }
}

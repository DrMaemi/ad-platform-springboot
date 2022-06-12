package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.config.response.RestResponse;
import com.ssg.backendpreassignment.dto.AdDisplayDto;
import com.ssg.backendpreassignment.service.AdDisplayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdDisplayController {
    private final AdDisplayService adDisplayService;

    @GetMapping("/ad/display")
    public String getAds(Model model) {
        List<AdDisplayDto> adDisplayDtos = adDisplayService.getAds();

        model.addAttribute("adDtos", adDisplayDtos);
        return "/ads";
    }

    @GetMapping("/api/ad/display")
    public ResponseEntity<?> getAds() {
        List<AdDisplayDto> resDtos = adDisplayService.getAds();

        return new ResponseEntity<RestResponse>(RestResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .result(resDtos)
                .build(), HttpStatus.OK);
    }
}

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

/**
 * 광고전시 도메인 관련 HTTP request 매핑 및 처리를 위한 컨트롤러 클래스
 */
@Controller
@RequiredArgsConstructor
public class AdDisplayController {
    private final AdDisplayService adDisplayService;

    /**
     * 광고전시 리스트 조회 페이지 요청 URL
     * 광고전시 리스트 조회 페이지 반환
     * @param model
     * @return .html
     */
    @GetMapping("/ad/display")
    public String getAds(Model model) {
        List<AdDisplayDto> adDisplayDtos = adDisplayService.getAds();

        model.addAttribute("adDtos", adDisplayDtos);
        return "/ads";
    }

    /**
     * 광고전시 리스트 조회 요청 API
     * 광고전시 리스트 반환
     * @return ResponseEntity
     */
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

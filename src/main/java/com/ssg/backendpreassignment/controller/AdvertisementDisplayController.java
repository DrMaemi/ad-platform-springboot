package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.dto.AdvertisementDisplayDto;
import com.ssg.backendpreassignment.service.AdvertisementDisplayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdvertisementDisplayController {
    private final AdvertisementDisplayService advertisementDisplayService;

    @GetMapping("/ads")
    public String getAds(Model model) {
        List<AdvertisementDisplayDto> advertisementDisplayDtos = advertisementDisplayService.getAds();

        model.addAttribute("adDtos", advertisementDisplayDtos);
        return "/ads";
    }
}

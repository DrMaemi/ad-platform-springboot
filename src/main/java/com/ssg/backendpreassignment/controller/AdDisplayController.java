package com.ssg.backendpreassignment.controller;

import com.ssg.backendpreassignment.dto.AdDisplayDto;
import com.ssg.backendpreassignment.service.AdDisplayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdDisplayController {
    private final AdDisplayService adDisplayService;

    @GetMapping("/ads")
    public String getAds(Model model) {
        List<AdDisplayDto> adDisplayDtos = adDisplayService.getAds();

        model.addAttribute("adDtos", adDisplayDtos);
        return "/ads";
    }
}

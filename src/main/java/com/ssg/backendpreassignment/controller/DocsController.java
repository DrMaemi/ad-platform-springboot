package com.ssg.backendpreassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocsController {
    @GetMapping("/docs/api")
    public String getApiDocs() {
        return "/docs/api.html";
    }
}

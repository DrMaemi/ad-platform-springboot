package com.ssg.backendpreassignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Spring REST Docs 페이지를 요청하는 HTTP request 매핑 및 처리를 위한 컨트롤러 클래스
 */
@Controller
public class DocsController {
    /**
     * REST Docs 페이지 요청 API
     * REST Docs 페이지 반환
     * @return .html
     */
    @GetMapping("/docs/api")
    public String getApiDocs() {
        return "/docs/api.html";
    }
}

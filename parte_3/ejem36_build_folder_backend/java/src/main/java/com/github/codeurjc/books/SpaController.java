package com.github.codeurjc.books;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/spa")
public class SpaController {

    @GetMapping(value = { "", "/" })
    public String index() {
        return "forward:/spa/index.html";
    }

    @GetMapping("/**")
    public ResponseEntity<Resource> serveSpa(HttpServletRequest request) {
        String path = request.getRequestURI().substring("/spa/".length());

        Resource resource = new ClassPathResource("static/spa/" + path);
        if (!resource.exists() || !resource.isReadable()) {
            resource = new ClassPathResource("static/spa/index.html");
        }

        return ResponseEntity.ok().body(resource);
    }
}

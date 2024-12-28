package com.acledabank.springjwtauth.domain.primary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @GetMapping
    Map<String,String> getAdmin() {
        return Map.of("message", "Hello Admin");
    }
}

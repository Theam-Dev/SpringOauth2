package com.acledabank.springjwtauth.domain.primary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AnonymousController {

    @GetMapping("anonymous")
    Map<String,String> anonymous() {
        return Map.of("anonymous", "true");
    }
    @GetMapping("security")
    Map<String,String> security() {
        return Map.of("message","security");
    }
}

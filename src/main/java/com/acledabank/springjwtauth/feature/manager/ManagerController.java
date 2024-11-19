package com.acledabank.springjwtauth.feature.manager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {
    @GetMapping
    Map<String,String> getManager() {
        return Map.of("message", "Hello Manager");
    }
}



package com.acledabank.springjwtauth.domain.secondary.flight.controller;

import com.acledabank.springjwtauth.domain.secondary.flight.model.Flight;
import com.acledabank.springjwtauth.domain.secondary.flight.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/fight")
public class FlightController {
    private final FlightService accountService;
    @GetMapping
    public ResponseEntity<List<Flight>> getAccounts() {
        List<Flight> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
}

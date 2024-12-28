package com.acledabank.springjwtauth.domain.secondary.flight.controller;

import com.acledabank.springjwtauth.domain.secondary.flight.model.Flight;
import com.acledabank.springjwtauth.domain.secondary.flight.model.Passenger;
import com.acledabank.springjwtauth.domain.secondary.flight.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/passenger")
public class PassengerController {
    private final PassengerService passengerService;
    @GetMapping
    public ResponseEntity<List<Passenger>> getAccounts() {
        List<Passenger> accounts = passengerService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }
}


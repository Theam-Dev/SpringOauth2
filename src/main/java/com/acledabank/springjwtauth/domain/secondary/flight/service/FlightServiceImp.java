package com.acledabank.springjwtauth.domain.secondary.flight.service;

import com.acledabank.springjwtauth.domain.secondary.flight.repository.FlightRepository;
import com.acledabank.springjwtauth.domain.secondary.flight.model.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FlightServiceImp implements FlightService {
    private final FlightRepository accountRepository;
    @Override
    public List<Flight> getAllAccounts() {
        return accountRepository.findAll();
    }
}

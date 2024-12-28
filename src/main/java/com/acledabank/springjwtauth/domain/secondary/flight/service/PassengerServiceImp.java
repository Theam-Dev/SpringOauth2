package com.acledabank.springjwtauth.domain.secondary.flight.service;

import com.acledabank.springjwtauth.domain.secondary.flight.model.Passenger;
import com.acledabank.springjwtauth.domain.secondary.flight.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerServiceImp implements PassengerService {
    private final PassengerRepository passengerRepository;
    @Override
    public List<Passenger> getAllAccounts() {

        return passengerRepository.findAll();
    }
}

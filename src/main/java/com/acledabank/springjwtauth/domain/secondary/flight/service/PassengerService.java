package com.acledabank.springjwtauth.domain.secondary.flight.service;

import com.acledabank.springjwtauth.domain.secondary.flight.model.Flight;
import com.acledabank.springjwtauth.domain.secondary.flight.model.Passenger;

import java.util.List;

public interface PassengerService {
    List<Passenger> getAllAccounts();
}

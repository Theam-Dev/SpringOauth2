package com.acledabank.springjwtauth.domain.secondary.flight.repository;

import com.acledabank.springjwtauth.domain.secondary.flight.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}


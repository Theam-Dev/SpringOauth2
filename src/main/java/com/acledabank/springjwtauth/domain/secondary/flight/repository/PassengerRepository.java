package com.acledabank.springjwtauth.domain.secondary.flight.repository;

import com.acledabank.springjwtauth.domain.secondary.flight.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}

package com.acledabank.springjwtauth.domain.secondary.flight.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "passenger",schema = "postgres_air")
public class Passenger {
    @Id
    private Integer booking_id;
    private String first_name;
    private String last_name;
    private String account_id;
}

package com.acledabank.springjwtauth.domain.secondary.flight.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "flight",schema = "postgres_air")
public class Flight {
    @Id
    private Integer flight_id;
    private String flight_no;
    private String status;
}
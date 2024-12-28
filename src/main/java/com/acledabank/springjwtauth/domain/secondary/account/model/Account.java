package com.acledabank.springjwtauth.domain.secondary.account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account",schema = "postgres_air")
public class Account {
    @Id
    private Integer account_id;
    private String first_name;
    private String last_name;
}

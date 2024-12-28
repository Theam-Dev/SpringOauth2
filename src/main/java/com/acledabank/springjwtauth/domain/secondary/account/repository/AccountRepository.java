package com.acledabank.springjwtauth.domain.secondary.account.repository;

import com.acledabank.springjwtauth.domain.secondary.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}

package com.acledabank.springjwtauth.domain.secondary.account.service;

import com.acledabank.springjwtauth.domain.secondary.account.repository.AccountRepository;
import com.acledabank.springjwtauth.domain.secondary.account.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {
    private final AccountRepository accountRepository;
    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}

package com.acledabank.springjwtauth.domain.secondary.account.service;

import com.acledabank.springjwtauth.domain.secondary.account.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
}

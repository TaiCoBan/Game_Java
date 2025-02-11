package com.utc.api.service.impl;

import com.utc.api.entity.Account;
import com.utc.api.repository.AccountRepository;
import com.utc.api.service.AccountService;
import com.utc.api.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository repository) {
        super(repository);
        this.accountRepository = repository;
    }

    @Override
    public Account register(Account account) {
        System.out.println("REGISTER");
        return null;
    }

    @Override
    public Account login(Account account) {
        System.out.println("LOGIN");
        return null;
    }

    @Override
    public void logout(Account account) {
        System.out.println("LOGOUT");
    }

    @Override
    public Account forgotPassword(Account account) {
        System.out.println("FORGOT PASSWORD");
        return null;
    }

    @Override
    public boolean isExistsByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public boolean isExistsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }
}

package com.utc.api.service.impl;

import com.utc.api.dto.request.LoginRequest;
import com.utc.api.dto.request.RegisterRequest;
import com.utc.api.entity.Account;
import com.utc.api.repository.AccountRepository;
import com.utc.api.repository.RoleRepository;
import com.utc.api.service.AccountService;
import com.utc.api.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public AccountServiceImpl(AccountRepository repository, RoleRepository roleRepository) {
        super(repository);
        this.accountRepository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Account register(RegisterRequest request) {
        Account account = new Account();

        account.setUsername(request.getUsername());
        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());

        return accountRepository.save(account);
    }

    @Override
    public Account login(LoginRequest request) {
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

package com.utc.api.service.impl;

import com.utc.api.dto.request.ChangePasswordRequest;
import com.utc.api.dto.request.RegisterRequest;
import com.utc.api.dto.response.AccountResponse;
import com.utc.api.entity.Account;
import com.utc.api.mapper.AccountMapper;
import com.utc.api.repository.AccountRepository;
import com.utc.api.repository.RoleRepository;
import com.utc.api.service.AccountService;
import com.utc.api.service.base.impl.BaseServiceImpl;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

    public AccountServiceImpl(AccountRepository repository,
                              RoleRepository roleRepository,
                              PasswordEncoder passwordEncoder) {
        super(repository);
        this.accountRepository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AccountResponse register(RegisterRequest request) {
        Account account = new Account();

        account.setUsername(request.getUsername());
        account.setEmail(request.getEmail());
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    @Override
    public List<AccountResponse> listDTO() {
        return super.list()
                   .stream()
                   .map(accountMapper::toAccountResponse)
                   .collect(Collectors.toList());
    }

    @Override
    public AccountResponse findDTO(Long id) {
        return accountMapper.toAccountResponse(super.find(id));
    }

    @Override
    public AccountResponse updateDTO(ChangePasswordRequest request) {
        return accountMapper.toAccountResponse(super.update(accountMapper.toAccount(request)));
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

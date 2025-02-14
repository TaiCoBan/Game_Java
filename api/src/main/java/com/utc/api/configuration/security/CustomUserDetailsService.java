package com.utc.api.configuration.security;

import com.utc.api.entity.Account;
import com.utc.api.exception.ApiException;
import com.utc.api.exception.ErrorCode;
import com.utc.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);

        return account.map(acc -> new CustomUserDetails(acc))
                   .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND));
    }
}

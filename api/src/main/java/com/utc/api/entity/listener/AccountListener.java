package com.utc.api.entity.listener;

import com.utc.api.entity.Account;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountListener {

    private static PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        AccountListener.passwordEncoder = passwordEncoder;
    }

    @PrePersist
    @PreUpdate
    public void encodePassword(Object o) {
        if (o instanceof Account) {
            Account account = (Account) o;
            account.setPassword(passwordEncoder.encode(account.getPassword()));
        }

    }
}

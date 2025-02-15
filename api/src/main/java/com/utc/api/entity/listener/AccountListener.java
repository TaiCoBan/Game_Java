package com.utc.api.entity.listener;

import com.utc.api.constants.Constant;
import com.utc.api.entity.Account;
import com.utc.api.service.RoleService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AccountListener {

    private static PasswordEncoder passwordEncoder;
    private static RoleService roleService;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder, RoleService roleService) {
        AccountListener.passwordEncoder = passwordEncoder;
        AccountListener.roleService = roleService;
    }

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.getRoles().add(roleService.findByName(Constant.ROLE_USER));
    }
}

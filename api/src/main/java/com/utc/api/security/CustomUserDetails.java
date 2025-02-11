package com.utc.api.security;

import com.utc.api.entity.Account;
import com.utc.api.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(Account account) {
        this.username = account.getUsername();
        this.password = account.getPassword();

        List<GrantedAuthority> list = new ArrayList<>();
        for (Role role : account.getRoles()) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName());
            list.add(authority);
        }
        this.authorities = list;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}

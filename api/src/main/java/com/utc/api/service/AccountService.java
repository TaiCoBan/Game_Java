package com.utc.api.service;

import com.utc.api.dto.request.ChangePasswordRequest;
import com.utc.api.dto.request.LoginRequest;
import com.utc.api.dto.request.RegisterRequest;
import com.utc.api.dto.response.AccountResponse;
import com.utc.api.entity.Account;
import com.utc.api.service.base.BaseService;
import jakarta.validation.Valid;

import java.util.List;

public interface AccountService extends BaseService<Account> {

    AccountResponse register(RegisterRequest request);

    void logout(Account account);

    AccountResponse changePassword(ChangePasswordRequest request);

    boolean isExistsByUsername(String username);

    boolean isExistsByEmail(String email);

    AccountResponse getProfile();

    AccountResponse login(LoginRequest request);
}

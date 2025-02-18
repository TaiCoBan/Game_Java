package com.utc.api.service;

import com.utc.api.dto.request.RegisterRequest;
import com.utc.api.dto.response.AccountResponse;
import com.utc.api.entity.Account;
import com.utc.api.service.base.BaseService;

public interface AccountService extends BaseService<Account> {

    AccountResponse register(RegisterRequest request);
    Account login(LoginRequest request);
    void logout(Account account);
    Account forgotPassword(Account account);
    boolean isExistsByUsername(String username);
    boolean isExistsByEmail(String email);
}

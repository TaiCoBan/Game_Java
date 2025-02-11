package com.utc.api.service;

import com.utc.api.entity.Account;
import com.utc.api.service.base.BaseService;

public interface AccountService extends BaseService<Account> {
    Account register(Account account);
    Account login(Account account);
    void logout(Account account);
    Account forgotPassword(Account account);
    boolean isExistsByUsername(String username);
    boolean isExistsByEmail(String email);
}

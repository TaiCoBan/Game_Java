package com.btl.menu.service;

import com.btl.menu.constant.Constant;
import com.btl.menu.entity.Account;
import com.btl.menu.service.base.BaseService;

public class AccountService extends BaseService<Account> {

    public void login(String username, String password) {
        if (LocalStorageService.get(Constant.ACCOUNT_KEY) == null) {
//            request.POST(LOGIN_URL, new LoginRequest(username, password));
        }
    }
}

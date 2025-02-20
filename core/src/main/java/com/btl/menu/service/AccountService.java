package com.btl.menu.service;

import com.badlogic.gdx.Game;
import com.btl.menu.api.Request;
import com.btl.menu.constant.Constant;
import com.btl.menu.dto.request.RegisterRequest;
import com.btl.menu.dto.response.AccountResponse;
import com.btl.menu.dto.response.ApiResponse;
import com.btl.menu.entity.Account;
import com.btl.menu.service.base.BaseService;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import static com.btl.menu.constant.Constant.*;

public class AccountService {

    private final Game game;
    private final Request request;

    public AccountService(Game game, Request request) {
        this.game = game;
        this.request = request;
    }

    public void login(String username, String password) {
        System.out.println("LOGIN");
    }

    public void register(RegisterRequest rq) {
        request.sendRequest(POST, REGISTER_URL, rq, new TypeReference<>() {});
    }

    public void getAccount() {
        request.sendRequest(GET, ACCOUNT_URL + "1", null, new TypeReference<AccountResponse>() {});
    }

    public void getAllAccount() {
        request.sendRequest(GET, ACCOUNT_URL, null, new TypeReference< List<AccountResponse> >() {});
    }
}

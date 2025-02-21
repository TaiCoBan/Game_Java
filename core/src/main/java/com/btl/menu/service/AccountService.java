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
    private final LocalStorageService localStorageService;

    public AccountService(Game game, Request request, LocalStorageService localStorageService) {
        this.game = game;
        this.request = request;
        this.localStorageService = localStorageService;
    }

    public void login(String username, String password) {
        System.out.println("LOGIN");
    }

    public void register(RegisterRequest rq) {
//        request.sendRequest(POST, REGISTER_URL, rq);
    }

    public void getAccount() {
        request.sendRequest(GET, ACCOUNT_URL + "1", null, new TypeReference< ApiResponse<AccountResponse> >() {
        });
    }

    public void getAllAccount() {
        request.sendRequest(GET, ACCOUNT_URL, null, new TypeReference< ApiResponse<List<AccountResponse>> >() {});
    }
}

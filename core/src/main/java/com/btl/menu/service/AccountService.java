package com.btl.menu.service;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.btl.menu.api.Request;
import com.btl.menu.dto.request.RegisterRequest;
import com.btl.menu.dto.response.AccountResponse;
import com.btl.menu.dto.response.ApiResponse;
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
        Gdx.app.log("REGISTER", rq.toString());
        request.sendRequest(
            POST,
            REGISTER_URL,
            rq,
            ACCOUNT_CACHE_KEY,
            new TypeReference< ApiResponse<AccountResponse> >() {});
    }
}

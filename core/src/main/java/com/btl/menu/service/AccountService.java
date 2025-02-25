package com.btl.menu.service;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.btl.menu.api.Request;
import com.btl.menu.dto.request.LoginRequest;
import com.btl.menu.dto.request.RegisterRequest;
import com.btl.menu.dto.response.AccountResponse;
import com.btl.menu.dto.response.ApiResponse;
import com.btl.menu.screens.MenuScreen;
import com.btl.menu.screens.base.Screens;
import com.fasterxml.jackson.core.type.TypeReference;

import static com.btl.menu.constant.Constant.*;

public class AccountService {

    private final Game game;
    private final Request request;
    private final LocalStorageService localStorageService;

    public AccountService(Game game,
                          Request request,
                          LocalStorageService localStorageService) {
        this.game = game;
        this.request = request;
        this.localStorageService = localStorageService;
    }

    public <T> void login(LoginRequest rq) {
        Gdx.app.log("INFO", "(login) object: " + rq.toString());
        ApiResponse<T> apiResponse = request.sendRequest(POST, LOGIN_URL, rq);

        Gdx.app.debug(DEBUG, "ApiResponse: " + apiResponse.toString());

        if (apiResponse.getCode() == 200) {
            localStorageService.put("username", rq.getUsername());
            localStorageService.put("password", rq.getPassword());

            localStorageService.put(ACCOUNT_CACHE_KEY, apiResponse.getResult());

            Gdx.app.log(DEBUG, "Cache:" + localStorageService.get(ACCOUNT_CACHE_KEY, AccountResponse.class).toString());
        }
    }
}

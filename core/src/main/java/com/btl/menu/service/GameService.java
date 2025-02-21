package com.btl.menu.service;

import com.badlogic.gdx.Game;
import com.btl.menu.api.Request;

public class GameService {

    private final Request request;
    private final LocalStorageService localStorageService;
    public AccountService accountService;

    public GameService(Game game) {
        this.localStorageService = new LocalStorageService();
        this.request = new Request(game, localStorageService);
        accountService = new AccountService(game, request, localStorageService);
    }
}

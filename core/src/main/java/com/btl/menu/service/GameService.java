package com.btl.menu.service;

import com.badlogic.gdx.Game;
import com.btl.menu.api.Request;

public class GameService {

    private final Request request;
    public AccountService accountService;

    public GameService(Game game) {
        this.request = new Request(game);
        accountService = new AccountService(game, request);
    }
}

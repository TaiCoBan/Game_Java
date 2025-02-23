package com.btl.menu.service;

import com.badlogic.gdx.Game;
import com.btl.menu.api.Request;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameService {

    private final ObjectMapper objectMapper;
    private final LocalStorageService localStorageService;
    private final Request request;
    public AccountService accountService;

    public GameService(Game game) {
        this.objectMapper = new ObjectMapper();
        this.localStorageService = new LocalStorageService(objectMapper);
        this.request = new Request(game, localStorageService, objectMapper);
        accountService = new AccountService(game, request, localStorageService);
    }
}

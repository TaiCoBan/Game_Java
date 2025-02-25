package com.btl.menu.service.base;

import com.badlogic.gdx.Game;
import com.btl.menu.api.Request;
import com.btl.menu.service.AccountService;
import com.btl.menu.service.LocalStorageService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameService {

    // MAPPER
    private final ObjectMapper objectMapper;

    // HTTP CLIENT
    private final Request request;

    // LOCAL STORAGE
    public LocalStorageService localStorageService;

    // RESPONSE SERVICE
    public AccountService accountService;

    public GameService(Game game) {
        this.objectMapper = new ObjectMapper();
        this.request = new Request(objectMapper);
        this.localStorageService = new LocalStorageService(objectMapper);
        accountService = new AccountService(game, request, localStorageService);
    }
}

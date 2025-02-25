package com.btl.menu.screens.base;

import com.badlogic.gdx.Game;
import com.btl.menu.screens.GameScreen;
import com.btl.menu.screens.LoginScreen;
import com.btl.menu.screens.MenuScreen;
import com.btl.menu.screens.RegisterScreen;
import com.btl.menu.service.base.GameService;

public class Screens {

    public RegisterScreen registerScreen;
    public LoginScreen loginScreen;
    public MenuScreen menuScreen;
    public GameScreen gameScreen;

    private Game game;

    public Screens(Game game,
                   GameService gameService) {
        this.game = game;
        registerScreen = new RegisterScreen(game, gameService);
        loginScreen = new LoginScreen(game, gameService);
        menuScreen = new MenuScreen(game, gameService);
        gameScreen = new GameScreen(game, gameService);
    }
}

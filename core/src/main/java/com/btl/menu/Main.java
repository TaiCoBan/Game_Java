package com.btl.menu;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.btl.menu.screens.base.Screens;
import com.btl.menu.service.base.GameService;
import lombok.Getter;

@Getter
public class Main extends Game {

    Screens screens;
    GameService gameService;

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        gameService = new GameService(this);
        screens = new Screens(this, gameService);

        setScreen(screens.loginScreen);
    }
}

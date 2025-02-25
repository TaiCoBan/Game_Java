package com.btl.menu.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.btl.menu.service.base.GameService;

public abstract class SampleScreen implements Screen {

    Game game;
    GameService gameService;

    public SampleScreen(Game game,
                        GameService gameService) {
        this.game = game;
        this.gameService = gameService;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

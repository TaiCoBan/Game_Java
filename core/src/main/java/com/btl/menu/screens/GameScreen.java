package com.btl.menu.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.btl.menu.service.GameService;

public abstract class GameScreen implements Screen {

    Game game;
    protected GameService gameService;

    public GameScreen(Game game) {
        this.game = game;
        gameService = new GameService();
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

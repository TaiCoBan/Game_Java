package com.btl.menu.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MenuScreen extends SampleScreen{
    public MenuScreen(Game game){
        super(game);
    }
    @Override
    public void show () {


    }

    @Override
    public void render (float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void hide () {

    }
}

package com.btl.menu.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.btl.menu.Main;
import com.btl.menu.entity.Character;

public class GameScreen extends SampleScreen{

    SpriteBatch batch;
    Texture background;
    Character character;

    public GameScreen(Game game){
        super(game);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("Temp Asset/vecteezy_background.jpg");
        character = new Character(0, 0);
    }

    @Override
    public void render(float deltaTime) {
        deltaTime = Math.min(0.06f, Gdx.graphics.getDeltaTime());
        character.update(deltaTime);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(character.getCharacterTexture(), character.getPos().x, character.getPos().y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }
}

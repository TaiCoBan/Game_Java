package com.btl.menu.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        character.update(deltaTime);
        batch.begin();
        batch.draw(background, 0, 0);
//        batch.draw(character.getCharacterTexture(), character.pos.x, character.pos.y);\
        batch.draw(character.getCharacterTexture(), character.get);
//        System.out.println("position: " + character.pos.x + " - " + character.pos.y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }
}

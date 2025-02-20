package com.btl.menu.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class RegisterScreen extends GameScreen {

    private Stage stage;
    private TextField usernameField;
    private TextField passwordField;
    private TextField emailField;
    private Skin skin;

    public RegisterScreen(Game game) {
        super(game);
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        setUpForm();
    }

    private void setUpForm() {
        Table table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);
        emailField = new TextField("", skin);

        table.add("Username:").pad(10);
        table.add(usernameField).pad(10).row();
        table.add("Password:").pad(10);
        table.add(passwordField).pad(10).row();
        table.add("Email:").pad(10);
        table.add(emailField).pad(10).row();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}

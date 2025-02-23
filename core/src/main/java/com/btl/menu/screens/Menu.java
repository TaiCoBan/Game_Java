package com.btl.menu.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.btl.menu.api.Request;
import com.btl.menu.dto.request.RegisterRequest;
import com.btl.menu.service.GameService;

import static com.btl.menu.constant.Constant.*;

public class Menu extends GameScreen {

    private Stage stage;
    private SpriteBatch batch;
    private Texture background;
    private Skin skin;

    public Menu(Game game) {
        super(game);

        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        background = new Texture("libgdx.png");

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        // BUTTON
        TextButton registerBtn = new TextButton("Register", skin);
        TextButton getAccountBtn = new TextButton("Get Account", skin);
        TextButton getAllBtn = new TextButton("Get All", skin);
        mainTable.add(registerBtn).width(200).pad(5).row();
        mainTable.add(getAccountBtn).width(200).pad(5).row();
        mainTable.add(getAllBtn).width(200).pad(5).row();

        TextButton exitBtn = new TextButton("Exit Game", skin);
        mainTable.add(exitBtn).width(200).pad(5).row();
        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Vẽ nền
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Vẽ UI (các nút, label...)
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        background.dispose();
        skin.dispose();
    }
}

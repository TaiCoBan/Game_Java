package com.btl.menu.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.btl.menu.Main;
import com.btl.menu.dto.response.AccountResponse;
import com.btl.menu.service.base.GameService;

import static com.btl.menu.constant.Constant.ACCOUNT_CACHE_KEY;
import static com.btl.menu.constant.Constant.DEBUG;

public class MenuScreen extends SampleScreen {

    private Stage stage;
    private Skin skin;

    private TextButton playButton;
    private TextButton exitButton;

    public MenuScreen(Game game,
                      GameService gameService) {
        super(game, gameService);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("MENU", skin);
        playButton = new TextButton("Play", skin);
        exitButton = new TextButton("Exit", skin);

        table.add(titleLabel).colspan(2).padBottom(20).row();
        table.add(playButton).colspan(2).width(200).height(40).row();
        table.add(exitButton).colspan(2).width(200).height(40).row();

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(((Main) game).getScreens().gameScreen);
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
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

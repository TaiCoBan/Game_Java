package com.btl.menu.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.btl.menu.Main;
import com.btl.menu.dto.request.LoginRequest;
import com.btl.menu.service.base.GameService;

public class LoginScreen extends SampleScreen {

    private Stage stage;
    private Skin skin;

    // UI Components
    private TextField usernameField;
    private TextField passwordField;
    private TextButton loginButton;
    private TextButton toRegisterButton;
    private Label errorLabel;

    public LoginScreen(Game game, GameService gameService) {
        super(game, gameService);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Load skin (đảm bảo file uiskin.json có trong assets)
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Tạo các thành phần UI
        Label titleLabel = new Label("Login", skin);
        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);
        loginButton = new TextButton("Login", skin);
        toRegisterButton = new TextButton("Register", skin);
        errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);

        // Thiết lập placeholder và chế độ password
        usernameField.setMessageText("Username");
        passwordField.setMessageText("Password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        // Bố cục các thành phần
        table.add(titleLabel).colspan(2).padBottom(20).row();
        table.add(new Label("Username:", skin)).padRight(10);
        table.add(usernameField).width(300).padBottom(10).row();
        table.add(new Label("Password:", skin)).padRight(10);
        table.add(passwordField).width(300).padBottom(10).row();
        table.add(loginButton).colspan(2).width(200).height(40).row();
        table.add(toRegisterButton).colspan(2).width(200).height(40).row();
        table.add(errorLabel).colspan(2).padTop(10);

        // Xử lý sự kiện đăng ký
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleLogin();
            }
        });

        toRegisterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(((Main) game).getScreens().registerScreen);
            }
        });
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        gameService.accountService.login(new LoginRequest(username, password));
        game.setScreen(((Main) game).getScreens().menuScreen);
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

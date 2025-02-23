package com.btl.menu.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.btl.menu.dto.request.RegisterRequest;

public class RegisterScreen extends GameScreen {

    private Stage stage;
    private Skin skin;

    // UI Components
    private TextField emailField;
    private TextField usernameField;
    private TextField passwordField;
    private TextField confirmPasswordField;
    private TextButton registerButton;
    private TextButton toLoginButton;
    private Label errorLabel;

    public RegisterScreen(Game game) {
        super(game);
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
        Label titleLabel = new Label("Registration", skin);
        emailField = new TextField("", skin);
        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);
        confirmPasswordField = new TextField("", skin);
        registerButton = new TextButton("Submit", skin);
        toLoginButton = new TextButton("Login", skin);
        errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);

        // Thiết lập placeholder và chế độ password
        emailField.setMessageText("Email");
        usernameField.setMessageText("Username");
        passwordField.setMessageText("Password");
        confirmPasswordField.setMessageText("Confirm Password");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        confirmPasswordField.setPasswordMode(true);
        confirmPasswordField.setPasswordCharacter('*');

        // Bố cục các thành phần
        table.add(titleLabel).colspan(2).padBottom(20).row();
        table.add(new Label("Email:", skin)).padRight(10);
        table.add(emailField).width(300).padBottom(10).row();
        table.add(new Label("Username:", skin)).padRight(10);
        table.add(usernameField).width(300).padBottom(10).row();
        table.add(new Label("Password:", skin)).padRight(10);
        table.add(passwordField).width(300).padBottom(10).row();
        table.add(new Label("Confirm password:", skin)).padRight(10);
        table.add(confirmPasswordField).width(300).padBottom(20).row();
        table.add(registerButton).colspan(2).width(200).height(40).row();
        table.add(toLoginButton).colspan(2).width(200).row();
        table.add(errorLabel).colspan(2).padTop(10);

        // Xử lý sự kiện đăng ký
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleRegistration();
            }
        });

        toLoginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoginScreen(game));
            }
        });
    }

    private void handleRegistration() {
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Kiểm tra validation
        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Fill all fields");
            return;
        }

        if (!password.equals(confirmPassword)) {
            errorLabel.setText("Passwords do not match");
            return;
        }

        System.out.println(email);
        System.out.println(username);
        System.out.println(password);
        System.out.println(confirmPassword);

        gameService.accountService.register(new RegisterRequest(email, username, password, confirmPassword));
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

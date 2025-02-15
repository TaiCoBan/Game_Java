package com.btl.menu.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Menu extends GameScreen {

    private Stage stage;
    private Skin skin;
    private TextButton btnLogin, btnRegister, btnExit;

    public Menu(Game game) {
        super(game);
    }

    @Override
    public void show() {
        // Khởi tạo Stage và Skin
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiskin.json")); // Cần file skin trong assets

        // Tạo các nút
        btnLogin = new TextButton("Login", skin);
        btnRegister = new TextButton("Register", skin);
        btnExit = new TextButton("Exit", skin);

        // Thiết lập layout
        Table table = new Table();
        table.setFillParent(true); // Chiếm toàn bộ màn hình
        table.center(); // Căn giữa

        // Thêm các nút vào bảng
        table.add(btnLogin).width(200).height(60).padBottom(20);
        table.row();
        table.add(btnRegister).width(200).height(60).padBottom(20);
        table.row();
        table.add(btnExit).width(200).height(60);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage); // Xử lý input

        // Thêm sự kiện click
        addButtonListeners();
    }

    private void addButtonListeners() {
        btnExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit(); // Thoát game
            }
        });

        btnLogin.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Chuyển sang màn hình Login
                // game.setScreen(new LoginScreen(game));
                System.out.println("Login");
            }
        });

        btnRegister.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Chuyển sang màn hình Register
                // game.setScreen(new RegisterScreen(game));
                System.out.println("Register");
            }
        });
    }

    @Override
    public void render(float delta) {
        // Clear màn hình
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Vẽ stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        // Xóa bộ xử lý input khi màn hình bị ẩn
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        // Giải phóng tài nguyên
        stage.dispose();
        skin.dispose();
    }
}

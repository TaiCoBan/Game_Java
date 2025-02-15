package com.btl.menu;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.*;

public class MainMenuScreen extends ApplicationAdapter implements Screen {
    private Stage stage;
    private Skin skin;

    @Override
    public void show() {
        stage = new Stage(new FitViewport(800, 600));
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        // Tạo layout chính
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        // Tiêu đề game
        Label titleLabel = new Label("DUNLONGLE", skin, "title");
        Label demoLabel = new Label("DEMO", skin, "default");
        mainTable.add(titleLabel).padTop(50).row();
        mainTable.add(demoLabel).padBottom(50).row();

        // Bảng chứa các nút menu
        Table menuTable = new Table();
        addMenuButton("New Game", menuTable);
        addMenuButton("Settings", menuTable);
        addMenuButton("Wishlist", menuTable);
        addMenuButton("Discord", menuTable);
        addMenuButton("Exit Game", menuTable);

        mainTable.add(menuTable).center().row();

        // Dòng chữ ở dưới cùng
        Label gamepadLabel = new Label("Playing with a gamepad is recommended!", skin);
        mainTable.add(gamepadLabel).padTop(100).bottom().expand();

        // Xử lý input
        Gdx.input.setInputProcessor(stage);

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.input.setInputProcessor(stage);
    }

    private void addMenuButton(String text, Table table) {
        TextButton button = new TextButton(text, skin);
        table.add(button).width(200).pad(10).row();

        // Thêm xử lý sự kiện
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleButtonClick(text);
            }
        });
    }

    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "New Game":
                // Xử lý new game
                break;
            case "Exit Game":
                Gdx.app.exit();
                break;
            // Thêm các case xử lý khác
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        if (stage != null) {
            stage.getViewport().update(width, height, true);
        }
    }

    // Các phương thức khác của Screen interface
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}

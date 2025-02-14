package com.btl.menu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends ApplicationAdapter {
    private Stage stage;
    private SpriteBatch batch;
    private Skin skin;
    private Label resultLabel;

    @Override
    public void create() {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Tạo Skin thủ công
        skin = new Skin();

        // Tạo font
        BitmapFont font = new BitmapFont(); // Dùng font mặc định
        skin.add("default-font", font);

        // Tạo texture cho button
        Pixmap pixmap = new Pixmap(150, 60, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        skin.add("default", new Texture(pixmap));

        Pixmap pixmapPressed = new Pixmap(150, 60, Pixmap.Format.RGBA8888);
        pixmapPressed.setColor(Color.GRAY);
        pixmapPressed.fill();
        skin.add("pressed", new Texture(pixmapPressed));

        // Định nghĩa style cho TextButton
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("default");
        textButtonStyle.down = skin.newDrawable("pressed");
        textButtonStyle.font = skin.getFont("default-font");
        skin.add("default", textButtonStyle);

        // Định nghĩa style cho Label
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default-font");
        labelStyle.fontColor = Color.WHITE;
        skin.add("default", labelStyle);

        // Tạo button "/a"
        TextButton buttonA = new TextButton("Call API /a", skin);
        buttonA.setPosition(140, 300);
        buttonA.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                String response = callApi("http://localhost:8080/a");
                resultLabel.setText(response);
            }
        });

        // Tạo button "/b"
        TextButton buttonB = new TextButton("Call API /b", skin);
        buttonB.setPosition(140, 200);
        buttonB.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                String response = callApi("http://localhost:8080/b");
                resultLabel.setText(response);
            }
        });

        // Tạo label hiển thị kết quả
        resultLabel = new Label("", skin);
        resultLabel.setPosition(140, 100);

        // Thêm các thành phần vào stage
        stage.addActor(buttonA);
        stage.addActor(buttonB);
        stage.addActor(resultLabel);

        // Dọn dẹp Pixmap
        pixmap.dispose();
        pixmapPressed.dispose();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        skin.dispose();
    }

    // Phương thức gửi request GET đến API
    private String callApi(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                return "Error: " + responseCode;
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

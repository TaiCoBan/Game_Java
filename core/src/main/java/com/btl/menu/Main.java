package com.btl.menu;

import com.badlogic.gdx.Game;
import com.btl.menu.screens.LoginScreen;

public class Main extends Game {

    @Override
    public void create() {
        setScreen(new LoginScreen(this));
    }
}

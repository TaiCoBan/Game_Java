package com.btl.menu.service.base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.btl.menu.api.Request;

public class BaseService<T> {

    protected Request request;
    protected Game game;

    public BaseService(Game game) {
        this.game = game;
//        this.request = new Request(game);
    }

    public T create(T t) {
        Gdx.app.log("(create) object: ", t.toString());
        return null;
    }

    public T update(T t) {
        Gdx.app.log("(update) object: ", t.toString());
        return null;
    }
}

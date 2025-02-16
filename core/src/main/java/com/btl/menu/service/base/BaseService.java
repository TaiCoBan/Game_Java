package com.btl.menu.service.base;

import com.badlogic.gdx.Gdx;
import com.btl.menu.api.Request;

public class BaseService<T> {

    protected Request request;

    public BaseService() {
        this.request = new Request();
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

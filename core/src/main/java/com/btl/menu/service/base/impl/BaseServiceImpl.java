package com.btl.menu.service.base.impl;

import com.badlogic.gdx.Gdx;
import com.btl.menu.api.Request;
import com.btl.menu.service.CacheService;
import com.btl.menu.service.base.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {

    Request request;
    CacheService cacheService;

    public BaseServiceImpl(Request request, CacheService cacheService) {
        this.request = request;
        this.cacheService = cacheService;
    }

    @Override
    public T create(T t) {
        Gdx.app.log("(create) object: ", t.toString());
        return null;
    }

    @Override
    public T update(T t) {
        Gdx.app.log("(update) object: ", t.toString());
        return null;
    }
}

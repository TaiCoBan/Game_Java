package com.btl.menu.service.base;

public interface BaseService<T> {

    T create(T t);
    T update(T t);
}

package com.utc.api.service.base;

import com.utc.api.entity.base.BaseEntity;
import com.utc.api.exception.ApiException;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T create(T t);
    T find(Long id) throws ApiException;
    List<T> list();
    T update(T t);
    void delete(Long id);
}

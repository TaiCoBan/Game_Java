package com.utc.api.service.base.impl;

import com.utc.api.entity.base.BaseEntity;
import com.utc.api.exception.ApiException;
import com.utc.api.exception.ErrorCode;
import com.utc.api.repository.base.BaseRepository;
import com.utc.api.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    private final BaseRepository<T> repository;

    public BaseServiceImpl(BaseRepository<T> baseRepository) {
        this.repository = baseRepository;
    }

    @Override
    public T create(T t) {
        log.info("(create) object: {}", t);
        return repository.save(t);
    }

    @Override
    public T find(Long id) {
        log.info("(find) id: {}", id);
        return repository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND));
    }

    @Override
    public List<T> list() {
        return repository.findAll();
    }

    @Override
    public T update(T t) {
        log.info("(update) object: {}", t);
        return repository.save(t);
    }

    @Override
    public void delete(Long id) {
        log.info("(delete) id: {}", id);
        repository.deleteById(id);
    }
}

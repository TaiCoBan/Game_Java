package com.utc.api.service;

import com.utc.api.dto.response.ClassResponse;
import com.utc.api.entity.Class;
import com.utc.api.service.base.BaseService;

import java.util.List;

public interface ClassService extends BaseService<Class> {
    List<ClassResponse> findAll();

    ClassResponse findById(Long id);
}

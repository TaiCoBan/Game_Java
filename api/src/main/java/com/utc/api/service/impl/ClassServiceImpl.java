package com.utc.api.service.impl;

import com.utc.api.dto.response.ClassResponse;
import com.utc.api.entity.Class;
import com.utc.api.repository.ClassRepository;
import com.utc.api.repository.base.BaseRepository;
import com.utc.api.service.ClassService;
import com.utc.api.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl extends BaseServiceImpl<Class> implements ClassService {

    public ClassServiceImpl(ClassRepository repository) {
        super(repository);
    }

    @Override
    public List<ClassResponse> findAll() {
        return list()
                   .stream()
                   .map(ClassResponse::from)
                   .toList();
    }

    @Override
    public ClassResponse findById(Long id) {
        return ClassResponse.from(find(id));
    }
}

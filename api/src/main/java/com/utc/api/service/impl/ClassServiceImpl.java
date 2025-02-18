package com.utc.api.service.impl;

import com.utc.api.entity.Class;
import com.utc.api.repository.ClassRepository;
import com.utc.api.repository.base.BaseRepository;
import com.utc.api.service.ClassService;
import com.utc.api.service.base.impl.BaseServiceImpl;

public class ClassServiceImpl extends BaseServiceImpl<Class> implements ClassService {

    public ClassServiceImpl(ClassRepository repository) {
        super(repository);
    }
}

package com.utc.api.service;

import com.utc.api.entity.Role;
import com.utc.api.service.base.BaseService;

public interface RoleService extends BaseService<Role> {

    boolean existsByName(String name);
    Role findByName(String name);
}

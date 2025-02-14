package com.utc.api.repository;

import com.utc.api.entity.Role;
import com.utc.api.repository.base.BaseRepository;

public interface RoleRepository extends BaseRepository<Role> {

    boolean existsByName(String name);
    Role findByName(String name);
}

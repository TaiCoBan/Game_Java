package com.utc.api.service.impl;

import com.utc.api.entity.Role;
import com.utc.api.repository.RoleRepository;
import com.utc.api.service.RoleService;
import com.utc.api.service.base.impl.BaseServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean existsByName(String name) {
        return roleRepository.existsByName(name);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }


}

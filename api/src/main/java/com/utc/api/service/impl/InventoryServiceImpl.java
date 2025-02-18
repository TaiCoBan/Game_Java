package com.utc.api.service.impl;

import com.utc.api.entity.Inventory;
import com.utc.api.repository.InventoryRepository;
import com.utc.api.service.InventoryService;
import com.utc.api.service.base.impl.BaseServiceImpl;

public class InventoryServiceImpl extends BaseServiceImpl<Inventory> implements InventoryService {

    public InventoryServiceImpl(InventoryRepository repository) {
        super(repository);
    }
}

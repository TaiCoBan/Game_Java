package com.utc.api.service.impl;

import com.utc.api.entity.Item;
import com.utc.api.repository.ItemRepository;
import com.utc.api.service.ItemService;
import com.utc.api.service.base.impl.BaseServiceImpl;

public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    public ItemServiceImpl(ItemRepository repository) {
        super(repository);
    }
}

package com.utc.api.service.impl;

import com.utc.api.dto.response.ItemResponse;
import com.utc.api.entity.Item;
import com.utc.api.repository.ItemRepository;
import com.utc.api.service.ItemService;
import com.utc.api.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    public ItemServiceImpl(ItemRepository repository) {
        super(repository);
    }

    @Override
    public List<ItemResponse> findAll() {
        return list()
                   .stream()
                   .map(ItemResponse::from)
                   .toList();
    }

    @Override
    public ItemResponse findById(Long id) {
        return ItemResponse.from(find(id));
    }
}

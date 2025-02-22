package com.utc.api.service;

import com.utc.api.dto.response.ItemResponse;
import com.utc.api.entity.Item;
import com.utc.api.service.base.BaseService;

import java.util.List;

public interface ItemService extends BaseService<Item> {

    List<ItemResponse> findAll();

    ItemResponse findById(Long id);
}

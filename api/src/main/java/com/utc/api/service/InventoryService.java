package com.utc.api.service;

import com.utc.api.dto.request.UpdateInventoryRequest;
import com.utc.api.dto.response.InventoryResponse;
import com.utc.api.entity.Inventory;
import com.utc.api.service.base.BaseService;

import java.util.List;
import java.util.UUID;

public interface InventoryService extends BaseService<Inventory> {

    List<InventoryResponse> listByUser();

    InventoryResponse updateByUser(UpdateInventoryRequest request);

    InventoryResponse findById(Long id);
}

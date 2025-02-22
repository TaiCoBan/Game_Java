package com.utc.api.controller;


import com.utc.api.dto.request.UpdateInventoryRequest;
import com.utc.api.dto.response.ApiResponse;
import com.utc.api.dto.response.InventoryResponse;
import com.utc.api.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories/")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("{id}")
    public ApiResponse<?> findById(@PathVariable Long id) {
        return ApiResponse
                   .<InventoryResponse>builder()
                   .result(inventoryService.findById(id))
                   .build();
    }

    @GetMapping
    public ApiResponse<?> list() {
        return ApiResponse
                   .<List<InventoryResponse>>builder()
                   .result(inventoryService.listByUser())
                   .build();
    }

    @PutMapping
    public ApiResponse<?> update(@Valid @RequestBody UpdateInventoryRequest request) {
        return ApiResponse
                   .<InventoryResponse>builder()
                   .result(inventoryService.updateByUser(request))
                   .build();
    }
}


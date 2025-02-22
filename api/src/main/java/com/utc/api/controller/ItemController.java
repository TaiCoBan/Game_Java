package com.utc.api.controller;

import com.utc.api.dto.response.ApiResponse;
import com.utc.api.dto.response.ItemResponse;
import com.utc.api.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items/")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("{id}")
    public ApiResponse<?> findById(@PathVariable Long id) {
        return ApiResponse
                   .<ItemResponse>builder()
                   .result(itemService.findById(id))
                   .build();
    }

    @GetMapping
    public ApiResponse<?> list() {
        return ApiResponse
                   .<List<ItemResponse>>builder()
                   .result(itemService.findAll())
                   .build();
    }
}


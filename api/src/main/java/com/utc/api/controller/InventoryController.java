package com.utc.api.controller;


import com.utc.api.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventories/")
public class InventoryController {

    @PostMapping
    public ApiResponse<?> create() {
        return ApiResponse.<String>builder().result("Create").build();
    }

    @GetMapping
    public ApiResponse<?> list() {
        return ApiResponse.<String>builder().result("List").build();
    }

    @GetMapping("{id}")
    public ApiResponse<?> findById(@PathVariable Long id) {
        return ApiResponse.<String>builder().result("Find by id").build();
    }

    @PutMapping
    public ApiResponse<?> update(@RequestBody Character character) {
        return ApiResponse.<String>builder().result("Update").build();
    }

    @DeleteMapping
    public ApiResponse<?> delete() {
        return ApiResponse.<String>builder().result("Delete").build();
    }
}


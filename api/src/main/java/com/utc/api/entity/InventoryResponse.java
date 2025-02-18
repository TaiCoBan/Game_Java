package com.utc.api.entity;

import com.utc.api.dto.response.ItemResponse;
import lombok.Data;

import java.util.List;

@Data
public class InventoryResponse {

    private String name;
    private List<ItemResponse> items;
}

package com.utc.api.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class InventoryResponse {

    private String name;
    private List<ItemResponse> items;
}

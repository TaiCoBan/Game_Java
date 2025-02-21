package com.utc.api.dto.response;

import com.utc.api.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class InventoryResponse {

    private Long id;
    private String name;
    private List<Long> items;

    public static InventoryResponse from(Inventory inventory) {
        return new InventoryResponse(
            inventory.getId(),
            inventory.getName(),
            inventory.getItems().stream().map(item -> item.getId()).collect(Collectors.toList())
        );
    }
}

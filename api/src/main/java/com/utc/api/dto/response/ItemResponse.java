package com.utc.api.dto.response;

import com.utc.api.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemResponse {

    private Long id;
    private String name;
    private float baseHealth;
    private float baseAttack;
    private String _class;

    public static ItemResponse from(Item item) {
        return new ItemResponse(
            item.getId(),
            item.getName(),
            item.getHealth(),
            item.getAttack(),
            item.get_class().getName()
        );
    }
}

package com.utc.api.dto.response;

import com.utc.api.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemResponse {

    private Long id;
    private String name;
    private int baseHealth;
    private int baseAttack;
    private String _class;

    public static ItemResponse from(Item item) {
        return new ItemResponse(
            item.getId(),
            item.getName(),
            item.get_class().getBaseHealth(),
            item.get_class().getBaseAttack(),
            item.get_class().getName()
        );
    }
}

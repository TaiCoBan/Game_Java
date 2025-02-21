package com.utc.api.dto.response;

import com.utc.api.entity.Class;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ClassResponse {

    private Long id;
    private String name;
    private int baseHealth;
    private int baseAttack;

    public static ClassResponse from(Class clazz) {
        return new ClassResponse(
            clazz.getId(),
            clazz.getName(),
            clazz.getBaseHealth(),
            clazz.getBaseAttack()
        );
    }
}

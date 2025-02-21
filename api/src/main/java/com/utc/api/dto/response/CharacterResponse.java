package com.utc.api.dto.response;

import com.utc.api.entity.Character;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CharacterResponse {

    private Long id;
    private String name;
    private int health;
    private int attack;

    public static CharacterResponse from(Character character) {
        return new CharacterResponse(
            character.getId(),
            character.getName(),
            character.get_class().getBaseHealth(),
            character.get_class().getBaseAttack()
        );
    }
}

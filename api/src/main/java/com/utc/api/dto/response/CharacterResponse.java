package com.utc.api.dto.response;

import lombok.Data;

@Data
public class CharacterResponse {

    private String name;
    private int health;
    private int attack;
}

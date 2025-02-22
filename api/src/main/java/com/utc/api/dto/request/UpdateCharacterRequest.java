package com.utc.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCharacterRequest {

    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private int health;
    @NotBlank
    private int attack;
}

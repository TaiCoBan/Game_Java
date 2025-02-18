package com.utc.api.dto.response;

import com.utc.api.entity.InventoryResponse;
import lombok.Data;

import java.util.Set;

@Data
public class AccountResponse {

    private String username;
    private String email;
    private Set<CharacterResponse> characters;
    private Set<InventoryResponse> inventory;
}

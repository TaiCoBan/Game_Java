package com.utc.api.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class AccountResponse {

    private Long id;
    private String username;
    private String email;
    private Set<CharacterResponse> characters;
    private Set<InventoryResponse> inventories;
}

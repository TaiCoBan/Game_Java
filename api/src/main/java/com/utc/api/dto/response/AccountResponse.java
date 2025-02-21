package com.utc.api.dto.response;

import com.utc.api.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class AccountResponse {

    private Long id;
    private String username;
    private String email;
    private Set<Long> characters;
    private Set<Long> inventories;

    public static AccountResponse from(Account account) {
        return new AccountResponse(
            account.getId(),
            account.getUsername(),
            account.getEmail(),
            account.getCharacters().stream().map(character -> character.getId()).collect(Collectors.toSet()),
            account.getInventories().stream().map(inventory -> inventory.getId()).collect(Collectors.toSet())
        );
    }
}

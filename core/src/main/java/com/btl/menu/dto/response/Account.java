package com.btl.menu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Account {

    private Long id;
    private String username;
    private String email;
    private Set<Character> characters;
    private Set<Inventory> inventory;
}

package com.btl.menu.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResponse {

    private Long id;
    private String username;
    private String email;
    private Set<Long> characters;
    private Set<Long> inventories;
}

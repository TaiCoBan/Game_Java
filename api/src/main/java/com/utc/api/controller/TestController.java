package com.utc.api.controller;

import com.utc.api.dto.response.AccountResponse;
import com.utc.api.dto.response.ApiResponse;
import com.utc.api.dto.response.CharacterResponse;
import com.utc.api.dto.response.InventoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class TestController {

    @GetMapping("/public")
    public ApiResponse<?> publicMethod() {
        AccountResponse accountResponse = new AccountResponse();

        accountResponse.setId(1L);
        accountResponse.setEmail("test@test.com");
        accountResponse.setUsername("testusername");
        Set<CharacterResponse> characterResponses = new HashSet<>();
        accountResponse.setCharacters(characterResponses);
        Set<InventoryResponse> inventoryResponses = new HashSet<>();
        accountResponse.setInventories(inventoryResponses);

        return ApiResponse
                   .<AccountResponse>builder()
                   .code(200)
                   .message("custom message")
                   .result(accountResponse)
                   .build();
    }

    @GetMapping("/admin")
    public String adminMethod() {
        return "admin";
    }

    @GetMapping("/user")
    public String userMethod() {
        return "user";
    }
}

package com.utc.api.controller;

import com.utc.api.dto.request.RegisterRequest;
import com.utc.api.dto.response.AccountResponse;
import com.utc.api.dto.response.ApiResponse;
import com.utc.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    private final AccountService accountService;

    public AuthController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("register")
    public ApiResponse<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        registerRequest.validate();

        return ApiResponse
                   .<AccountResponse>builder()
                   .result(accountService.register(registerRequest))
                   .build();
    }

    @PostMapping("logout")
    public String logout() {
        return "LOGOUT";
    }
}

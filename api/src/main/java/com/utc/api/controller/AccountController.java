package com.utc.api.controller;

import com.utc.api.dto.request.ChangePasswordRequest;
import com.utc.api.dto.response.AccountResponse;
import com.utc.api.dto.response.ApiResponse;
import com.utc.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/accounts/")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("profile")
    public ApiResponse<?> getProfile() {
        return ApiResponse
                   .<AccountResponse>builder()
                   .result(accountService.getProfile())
                   .build();
    }
}

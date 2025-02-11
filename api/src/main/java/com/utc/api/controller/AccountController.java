package com.utc.api.controller;

import com.utc.api.dto.request.RegisterRequest;
import com.utc.api.dto.response.ApiResponse;
import com.utc.api.entity.Account;
import com.utc.api.exception.ApiException;
import com.utc.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("register")
    public ApiResponse<?> register(@Valid @RequestBody RegisterRequest request) {
        request.validate();

        Account account = new Account(
            request.getEmail(),
            request.getUsername(),
            request.getPassword()
        );

        return ApiResponse.<Account>builder().result(accountService.create(account)).build();
    }

    @GetMapping()
    public ApiResponse<?> getAccount(@RequestParam Long id) {
        return ApiResponse.<Account>builder().result(accountService.find(id)).build();
    }
}

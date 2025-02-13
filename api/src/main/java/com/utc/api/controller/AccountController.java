package com.utc.api.controller;

import com.utc.api.dto.request.LoginRequest;
import com.utc.api.dto.request.RegisterRequest;
import com.utc.api.dto.response.ApiResponse;
import com.utc.api.entity.Account;
import com.utc.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

        Account account = accountService.register(request);

        return ApiResponse.<Account>builder().result(account).build();
    }

    @PostMapping("login")
    public ApiResponse<?> login(@Valid @RequestBody LoginRequest request) {
        Account account = accountService.login(request);

        return ApiResponse.<Account>builder().result(account).build();
    }

    @GetMapping()
    public ApiResponse<?> getAccount(@RequestParam Long id) {
        return ApiResponse.<Account>builder().result(accountService.find(id)).build();
    }

    @GetMapping("list")
    public ResponseEntity<?> getAccountList() {
        return ResponseEntity.ok().body(accountService.list());
    }
}

package com.utc.api.controller;

import com.utc.api.dto.request.ChangePasswordRequest;
import com.utc.api.dto.request.LoginRequest;
import com.utc.api.dto.request.RegisterRequest;
import com.utc.api.dto.response.AccountResponse;
import com.utc.api.dto.response.ApiResponse;
import com.utc.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
                   .code(HttpStatus.CREATED.value())
                   .result(accountService.register(registerRequest))
                   .build();
    }

    @PostMapping("login")
    public ApiResponse<?> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse
                   .<AccountResponse>builder()
                   .result(accountService.login(request))
                   .build();
    }

    @PostMapping("logout")
    public String logout() {
        return "LOGOUT";
    }

    @PutMapping("change-password")
    public ApiResponse<AccountResponse> update(@Valid @RequestBody ChangePasswordRequest request) {
        request.validate();
        return ApiResponse
                   .<AccountResponse>builder()
                   .code(HttpStatus.CREATED.value())
                   .result(accountService.changePassword(request))
                   .build();
    }
}

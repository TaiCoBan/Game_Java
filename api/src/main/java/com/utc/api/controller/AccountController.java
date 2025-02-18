package com.utc.api.controller;

import com.utc.api.dto.request.ChangePasswordRequest;
import com.utc.api.dto.response.AccountResponse;
import com.utc.api.dto.response.ApiResponse;
import com.utc.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts/")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ApiResponse<?> list() {
        return ApiResponse
                   .<List<AccountResponse>>builder()
                   .result(accountService.listDTO())
                   .build();
    }

    @GetMapping("{id}")
    public ApiResponse<AccountResponse> findById(@PathVariable("id") Long id) {
        return ApiResponse
                   .<AccountResponse>builder()
                   .result(accountService.findDTO(id))
                   .build();
    }

    @PutMapping
    public ApiResponse<AccountResponse> update(@RequestBody @Valid ChangePasswordRequest request) {
        return ApiResponse
                   .<AccountResponse>builder()
                   .result(accountService.updateDTO(request))
                   .build();
    }
}

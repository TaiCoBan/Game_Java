package com.utc.api.controller;

import com.utc.api.constants.Constant;
import com.utc.api.dto.request.RegisterRequest;
import com.utc.api.dto.response.ApiResponse;
import com.utc.api.entity.Account;
import com.utc.api.entity.Role;
import com.utc.api.exception.ApiException;
import com.utc.api.service.AccountService;
import com.utc.api.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/")
public class AccountController {

    private final AccountService accountService;
    private final RoleService roleService;

    public AccountController(AccountService accountService, RoleService roleService) {
        this.accountService = accountService;
        this.roleService = roleService;
    }

    @PostMapping("register")
    public ApiResponse<?> register(@Valid @RequestBody RegisterRequest request) {
        request.validate();

        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());

        Role role = roleService.findByName(Constant.ROLE_USER);
        if (role == null) {
            role = new Role("USER", "This is ROLE_USER");
            roleService.create(role);
        }

        account.getRoles().add(role);

        return ApiResponse.<Account>builder().result(accountService.create(account)).build();
    }

    @GetMapping()
    public ApiResponse<?> getAccount(@RequestParam Long id) {
        return ApiResponse.<Account>builder().result(accountService.find(id)).build();
    }
}

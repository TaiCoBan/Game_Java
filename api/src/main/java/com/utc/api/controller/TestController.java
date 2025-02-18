package com.utc.api.controller;

import com.utc.api.dto.response.AccountResponse;
import com.utc.api.entity.Account;
import com.utc.api.entity.Role;
import com.utc.api.mapper.AccountMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/admin")
    String admin() {
        return "ADMIN";
    }

    @GetMapping("/user")
    String user() {
        return "USER";
    }

    @GetMapping("/test1")
    AccountResponse test1() {
        return AccountMapper.INSTANCE.toAccountResponse(new Account());
    }
}

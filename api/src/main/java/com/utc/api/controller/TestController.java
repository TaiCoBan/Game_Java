package com.utc.api.controller;

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
}

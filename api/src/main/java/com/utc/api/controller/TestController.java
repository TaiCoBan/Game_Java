package com.utc.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public class TestController {
    @RequestMapping("/admin")
    String admin() {
        return "ADMIN";
    }

    @RequestMapping("/user")
    String user() {
        return "USER";
    }
}

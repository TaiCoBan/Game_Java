package com.utc.api.dto.request;

import lombok.Data;

@Data
public class UpdateAccountRequest {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
}

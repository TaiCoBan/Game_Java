package com.utc.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty(message = "Username must not be empty")
    @NotBlank(message = "Username must not be blank")
    @NotNull(message = "Username must not be null")
    private String username;
    @NotEmpty(message = "Password must not be empty")
    @NotBlank(message = "Password must not be blank")
    @NotNull(message = "Password must not be null")
    private String password;
}

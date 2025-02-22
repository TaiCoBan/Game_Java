package com.utc.api.dto.request;

import com.utc.api.exception.ApiException;
import com.utc.api.exception.ErrorCode;
import com.utc.api.annotation.Username;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    @Email
    @NotBlank(message = "Email must not be blank")
    private String email;

    @Username
    @NotBlank(message = "Username must not be blank")
    @Size(min = 8, max = 20, message = "Username must be 8-20 characters")
    private String username;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, message = "Password must be at least 8 letters")
    private String password;

    @NotBlank(message = "Confirm password must not be blank")
    private String confirmPassword;

    public void validate() {
        if (!Objects.equals(password, confirmPassword)) {
            throw new ApiException(ErrorCode.BAD_REQUEST);
        }
    }
}

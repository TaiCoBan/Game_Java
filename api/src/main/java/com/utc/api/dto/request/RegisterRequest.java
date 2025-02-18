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
    @NotEmpty(message = "Email must not be empty")
    @NotBlank(message = "Email must not be blank")
    @NotNull(message = "Email must not be null")
    private String email;
    @Username
    @NotEmpty(message = "Username must not be empty")
    @NotBlank(message = "Username must not be blank")
    @NotNull(message = "Username must not be null")
    @Size(min = 8, message = "Username must be at least 8 letters")
    private String username;
    @Size(min = 8, message = "Password must be at least 8 letters")
    @NotEmpty(message = "Password must not be empty")
    @NotBlank(message = "Password must not be blank")
    @NotNull(message = "Password must not be null")
    private String password;
    private String confirmPassword;

    public void validate() {
        if (!Objects.equals(password, confirmPassword)) {
            throw new ApiException(ErrorCode.PARAMETER_ERROR);
        }
    }
}

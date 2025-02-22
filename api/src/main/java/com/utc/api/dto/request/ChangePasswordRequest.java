package com.utc.api.dto.request;

import com.utc.api.exception.ApiException;
import com.utc.api.exception.ErrorCode;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Objects;

@Data
public class ChangePasswordRequest {

    private Long id;
    @Email
    @NotEmpty(message = "Email must not be empty")
    @NotBlank(message = "Email must not be blank")
    @NotNull(message = "Email must not be null")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 letters")
    @NotEmpty(message = "Password must not be empty")
    @NotBlank(message = "Password must not be blank")
    @NotNull(message = "Password must not be null")
    private String password;
    private String confirmPassword;

    public void validate() {
        if (Objects.equals(password, confirmPassword)) {
            throw new ApiException(ErrorCode.BAD_REQUEST);
        }
    }
}

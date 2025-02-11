package com.utc.api.dto.request;

import com.utc.api.exception.ApiException;
import com.utc.api.exception.ErrorCode;
import com.utc.api.annotation.Username;
import jakarta.validation.constraints.Email;
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

    @Username
    private String username;
    @Email
    private String email;
    private String password;
    private String confirmPassword;

    public void validate() {
        if (!Objects.equals(password, confirmPassword)) {
            throw new ApiException(ErrorCode.PARAMETER_ERROR);
        }
    }
}

package com.utc.api.dto.request;

import com.utc.api.filter.annotation.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    @Username
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public void validate() {

    }
}

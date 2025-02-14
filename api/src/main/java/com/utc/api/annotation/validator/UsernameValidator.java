package com.utc.api.annotation.validator;

import com.utc.api.annotation.Username;
import com.utc.api.service.AccountService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsernameValidator implements ConstraintValidator<Username, String> {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !accountService.isExistsByUsername(username);
    }
}

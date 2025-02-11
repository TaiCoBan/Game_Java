package com.utc.api.filter.annotation;

import com.utc.api.service.AccountService;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = Username.UsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Username {
    String message() default "Invalid username";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    class UsernameValidator implements ConstraintValidator<Username, String> {

        private final AccountService accountService;

        public UsernameValidator(AccountService accountService) {
            this.accountService = accountService;
        }

        @Override
        public void initialize(Username constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
            return !accountService.isExistsByUsername(username);
        }
    }
}

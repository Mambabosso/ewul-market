package com.github.mambabosso.ewul.server.service;

import com.github.mambabosso.ewul.server.config.RegexValidationConfig;
import com.github.mambabosso.ewul.server.model.core.password.PasswordService;
import com.github.mambabosso.ewul.server.model.core.user.User;
import com.github.mambabosso.ewul.server.model.core.user.UserService;
import com.github.mambabosso.ewul.server.result.Result;
import com.github.mambabosso.ewul.server.validator.RegexValidator;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class RegisterService {

    private final RegexValidationConfig regexValidationConfig;
    private final UserService userService;
    private final PasswordService passwordService;

    public Result<User> register(String username, String password) {

        RegexValidator usernameValidator = new RegexValidator(username, regexValidationConfig.getUsername());
        RegexValidator passwordValidator = new RegexValidator(password, regexValidationConfig.getPassword());

        return Result.failure(null);

    }

}

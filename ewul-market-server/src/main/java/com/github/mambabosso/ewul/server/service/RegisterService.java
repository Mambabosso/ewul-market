package com.github.mambabosso.ewul.server.service;

import com.github.mambabosso.ewul.server.config.RegexValidationConfig;
import com.github.mambabosso.ewul.server.error.AlreadyExistsException;
import com.github.mambabosso.ewul.server.error.UnknownErrorException;
import com.github.mambabosso.ewul.server.error.ValidationFailureException;
import com.github.mambabosso.ewul.server.model.core.password.Password;
import com.github.mambabosso.ewul.server.model.core.password.PasswordService;
import com.github.mambabosso.ewul.server.model.core.user.User;
import com.github.mambabosso.ewul.server.model.core.user.UserService;
import com.github.mambabosso.ewul.server.model.core.user.UserType;
import com.github.mambabosso.ewul.server.result.Result;
import com.github.mambabosso.ewul.server.validation.RegexValidator;
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

        Password p;
        try {
            p = passwordService.create(passwordValidator);

        } catch (ValidationFailureException | AlreadyExistsException ex) {
            return Result.failure(ex);
        } catch (Exception ex) {
            return Result.failure(new UnknownErrorException(ex));
        }

        User u = null;
        try {
            u = userService.create(usernameValidator, () -> p, () -> UserType.USER);

        } catch (ValidationFailureException | AlreadyExistsException ex) {
            return Result.failure(ex);
        } catch (Exception ex) {
            return Result.failure(new UnknownErrorException(ex));
        } finally {
            if (u == null) {
                passwordService.delete(p.getId());
            }
        }

        return Result.success(u);

    }

}

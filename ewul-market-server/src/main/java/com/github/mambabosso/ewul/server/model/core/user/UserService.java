package com.github.mambabosso.ewul.server.model.core.user;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;
import com.github.mambabosso.ewul.server.error.Errors;
import com.github.mambabosso.ewul.server.model.core.password.Password;
import com.github.mambabosso.ewul.server.model.core.role.Role;
import com.github.mambabosso.ewul.server.result.Result;
import com.github.mambabosso.ewul.server.validator.Validator;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.UUID;

public class UserService extends BaseDAOService<UserDAO> {

    @Inject
    public UserService(final UserDAO baseDAO) {
        super(baseDAO);
    }

    public Result<User> create(final Validator<String> name, final Validator<Password> password, final Validator<UserType> type, final Validator<Set<Role>> roles) {
        try {
            if (name == null || !name.isValid() || password == null || !password.isValid() || type == null || !type.isValid() || (roles != null && !roles.isValid())) {
                return Result.failure(Errors.USER_VALIDATION_FAILURE.get());
            }
            UserDAO dao = getBaseDAO();
            String userName = name.get();
            if (dao.getByName(userName) != null) {
                return Result.failure(Errors.USER_ALREADY_EXISTS.get());
            }
            User user = new User();
            user.setName(userName);
            user.setPassword(password.get());
            if (roles != null) {
                user.setRoles(roles.get());
            }
            user.setType(type.get());
            user.setCreatedAt(DateTime.now());
            UUID id = dao.insert(user);
            if (id != null) {
                return Result.success(dao.get(id));
            }
            return Result.failure(Errors.UNKNOWN_USER_FAILURE.get());
        } catch (PersistenceException ex) {
            return Result.failure(Errors.USER_PERSISTENCE_FAILURE.get(ex));
        } catch (ConstraintViolationException ex) {
            return Result.failure(Errors.USER_VALIDATION_FAILURE.get(ex));
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_USER_FAILURE.get(ex));
        }
    }

    public Result<User> create(final Validator<String> name, final Validator<Password> password, final Validator<UserType> type) {
        return create(name, password, type, null);
    }

    public Result<User> get(final UUID id) {
        try {
            UserDAO dao = getBaseDAO();
            User user = dao.get(id);
            if (user != null) {
                return Result.success(user);
            }
            return Result.failure(Errors.USER_NOT_FOUND.get());
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_USER_FAILURE.get(ex));
        }
    }

    public Result<User> getByName(final String name) {
        try {
            UserDAO dao = getBaseDAO();
            User user = dao.getByName(name);
            if (user != null) {
                return Result.success(user);
            }
            return Result.failure(Errors.USER_NOT_FOUND.get());
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_USER_FAILURE.get(ex));
        }
    }

    public Result<Long> delete(final UUID id) {
        try {
            UserDAO dao = getBaseDAO();
            return Result.success(dao.delete(id));
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_USER_FAILURE.get(ex));
        }
    }

}

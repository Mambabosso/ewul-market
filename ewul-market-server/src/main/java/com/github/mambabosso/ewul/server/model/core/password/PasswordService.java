package com.github.mambabosso.ewul.server.model.core.password;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;
import com.github.mambabosso.ewul.server.error.Errors;
import com.github.mambabosso.ewul.server.result.Result;
import com.github.mambabosso.ewul.server.validator.Validator;
import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.UUID;

public class PasswordService extends BaseDAOService<PasswordDAO> {

    @Inject
    public PasswordService(final PasswordDAO baseDAO) {
        super(baseDAO);
    }

    public Result<Password> create(final Validator<String> plain_password) {
        try {
            if (plain_password == null || !plain_password.isValid()) {
                return Result.failure(Errors.PASSWORD_VALIDATION_FAILURE.get());
            }
            PasswordDAO dao = getBaseDAO();
            Password password = new Password();
            password.setHash(BCrypt.hashpw(plain_password.get(), BCrypt.gensalt(14)));
            password.setLastAccess(DateTime.now());
            password.setCreatedAt(password.getLastAccess());
            UUID id = dao.insert(password);
            if (id != null) {
                return Result.success(dao.get(id));
            }
            return Result.failure(Errors.UNKNOWN_PASSWORD_FAILURE.get());
        } catch (PersistenceException ex) {
            return Result.failure(Errors.PASSWORD_PERSISTENCE_FAILURE.get(ex));
        } catch (ConstraintViolationException ex) {
            return Result.failure(Errors.PASSWORD_VALIDATION_FAILURE.get(ex));
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_PASSWORD_FAILURE.get(ex));
        }
    }

    public Result<Password> get(final UUID id) {
        try {
            PasswordDAO dao = getBaseDAO();
            Password password = dao.get(id);
            if (password != null) {
                return Result.success(password);
            }
            return Result.failure(Errors.PASSWORD_NOT_FOUND.get());
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_PASSWORD_FAILURE.get(ex));
        }
    }

    public Result<Long> delete(final UUID id) {
        try {
            PasswordDAO dao = getBaseDAO();
            return Result.success(dao.delete(id));
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_PASSWORD_FAILURE.get(ex));
        }
    }

}

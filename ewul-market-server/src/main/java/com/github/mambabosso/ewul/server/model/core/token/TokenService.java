package com.github.mambabosso.ewul.server.model.core.token;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;
import com.github.mambabosso.ewul.server.error.Errors;
import com.github.mambabosso.ewul.server.result.Result;
import com.github.mambabosso.ewul.server.validator.Validator;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.UUID;

public class TokenService extends BaseDAOService<TokenDAO> {

    @Inject
    public TokenService(final TokenDAO baseDAO) {
        super(baseDAO);
    }

    public Result<Token> create(final Validator<String> value, final Validator<TokenType> type, final Validator<DateTime> expiresAt) {
        try {
            if (value == null || !value.isValid() || type == null || !type.isValid() || !expiresAt.isValid()) {
                return Result.failure(Errors.TOKEN_VALIDATION_FAILURE.get());
            }
            TokenDAO dao = getBaseDAO();
            Token token = new Token();
            token.setValue(value.get());
            token.setType(type.get());
            token.setExpiresAt(expiresAt.get());
            token.setLastAccess(DateTime.now());
            token.setCreatedAt(token.getLastAccess());
            UUID id = dao.insert(token);
            if (id != null) {
                return Result.success(dao.get(id));
            }
            return Result.failure(Errors.UNKNOWN_TOKEN_FAILURE.get());
        } catch (PersistenceException ex) {
            return Result.failure(Errors.TOKEN_PERSISTENCE_FAILURE.get(ex));
        } catch (ConstraintViolationException ex) {
            return Result.failure(Errors.TOKEN_VALIDATION_FAILURE.get(ex));
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_TOKEN_FAILURE.get(ex));
        }
    }

    public Result<Token> get(final UUID id) {
        try {
            TokenDAO dao = getBaseDAO();
            Token token = dao.get(id);
            if (token != null) {
                return Result.success(token);
            }
            return Result.failure(Errors.TOKEN_NOT_FOUND.get());
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_TOKEN_FAILURE.get(ex));
        }
    }

    public Result<Long> delete(final UUID id) {
        try {
            TokenDAO dao = getBaseDAO();
            return Result.success(dao.delete(id));
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_TOKEN_FAILURE.get(ex));
        }
    }

}

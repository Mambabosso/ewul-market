package com.github.mambabosso.ewul.server.model.core.token;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;
import com.github.mambabosso.ewul.server.error.NotFoundException;
import com.github.mambabosso.ewul.server.error.UnknownErrorException;
import com.github.mambabosso.ewul.server.error.ValidationFailureException;
import com.github.mambabosso.ewul.server.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.UUID;

@Slf4j
public class TokenService extends BaseDAOService<TokenDAO> {

    @Inject
    public TokenService(final TokenDAO baseDAO) {
        super(baseDAO);
    }

    public Token create(final Validator<String> value, final Validator<TokenType> type, final Validator<DateTime> expiresAt) {

        if (value == null || !value.isValid() || type == null || !type.isValid() || !expiresAt.isValid()) {
            throw new ValidationFailureException();
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
            Token result = dao.get(id);
            log.info("#create -> {}", result);
            return result;
        }
        throw new UnknownErrorException();

    }

    public Token get(final UUID id) {

        TokenDAO dao = getBaseDAO();
        Token token = dao.get(id);
        if (token != null) {
            log.info("#get -> {}", token);
            return token;
        }
        throw new NotFoundException();

    }

    public long delete(final UUID id) {

        TokenDAO dao = getBaseDAO();
        long result = dao.delete(id);
        if (result > 0) {
            log.info("#delete -> {}", id);
            return result;
        }
        throw new NotFoundException();

    }

}

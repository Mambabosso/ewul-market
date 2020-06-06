package com.github.mambabosso.ewul.server.model.core.password;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;
import com.github.mambabosso.ewul.server.error.NotFoundException;
import com.github.mambabosso.ewul.server.error.UnknownErrorException;
import com.github.mambabosso.ewul.server.error.ValidationFailureException;
import com.github.mambabosso.ewul.server.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import java.util.UUID;

@Slf4j
public class PasswordService extends BaseDAOService<PasswordDAO> {

    @Inject
    public PasswordService(final PasswordDAO baseDAO) {
        super(baseDAO);
    }

    public Password create(final Validator<String> plain_password) {

        if (plain_password == null || !plain_password.isValid()) {
            throw new ValidationFailureException();
        }

        PasswordDAO dao = getBaseDAO();
        Password password = new Password();
        password.setHash(BCrypt.hashpw(plain_password.get(), BCrypt.gensalt(14)));
        password.setLastAccess(DateTime.now());
        password.setCreatedAt(password.getLastAccess());
        UUID id = dao.insert(password);
        if (id != null) {
            Password result = dao.get(id);
            log.info("#create -> {}", result);
            return result;
        }
        throw new UnknownErrorException();

    }

    public Password get(final UUID id) {

        PasswordDAO dao = getBaseDAO();
        Password password = dao.get(id);
        if (password != null) {
            log.info("#get -> {}", password);
            return password;
        }
        throw new NotFoundException();

    }

    public long delete(final UUID id) {

        PasswordDAO dao = getBaseDAO();
        long result = dao.delete(id);
        if (result > 0) {
            log.info("#delete -> {}", id);
            return result;
        }
        throw new NotFoundException();

    }

}

package com.github.mambabosso.ewul.server.model.core.user;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;
import com.github.mambabosso.ewul.server.error.AlreadyExistsException;
import com.github.mambabosso.ewul.server.error.NotFoundException;
import com.github.mambabosso.ewul.server.error.UnknownErrorException;
import com.github.mambabosso.ewul.server.error.ValidationFailureException;
import com.github.mambabosso.ewul.server.model.core.password.Password;
import com.github.mambabosso.ewul.server.model.core.role.Role;
import com.github.mambabosso.ewul.server.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class UserService extends BaseDAOService<UserDAO> {

    @Inject
    public UserService(final UserDAO baseDAO) {
        super(baseDAO);
    }

    public User create(final Validator<String> name, final Validator<Password> password,
                       final Validator<UserType> type, final Validator<Set<Role>> roles) {

        if (name == null || !name.isValid() || password == null || !password.isValid() ||
                type == null || !type.isValid() || (roles != null && !roles.isValid())) {

            throw new ValidationFailureException();
        }

        UserDAO dao = getBaseDAO();
        String userName = name.get();
        if (dao.getByName(userName) != null) {
            throw new AlreadyExistsException();
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
            User result = dao.get(id);
            log.info("#create -> {}", result);
            return result;
        }
        throw new UnknownErrorException();

    }

    public User create(final Validator<String> name, final Validator<Password> password,
                       final Validator<UserType> type) {

        return create(name, password, type, null);
    }

    public User get(final UUID id) {

        UserDAO dao = getBaseDAO();
        User user = dao.get(id);
        if (user != null) {
            log.info("#get -> {}", user);
            return user;
        }
        throw new NotFoundException();

    }

    public User getByName(final String name) {

        UserDAO dao = getBaseDAO();
        User user = dao.getByName(name);
        if (user != null) {
            log.info("#getByName -> {}", user);
            return user;
        }
        throw new NotFoundException();

    }

    public long delete(final UUID id) {

        UserDAO dao = getBaseDAO();
        long result = dao.delete(id);
        if (result > 0) {
            log.info("#delete -> {}", id);
            return result;
        }
        throw new NotFoundException();

    }

}

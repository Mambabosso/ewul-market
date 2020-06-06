package com.github.mambabosso.ewul.server.model.core.role;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;
import com.github.mambabosso.ewul.server.error.AlreadyExistsException;
import com.github.mambabosso.ewul.server.error.NotFoundException;
import com.github.mambabosso.ewul.server.error.UnknownErrorException;
import com.github.mambabosso.ewul.server.error.ValidationFailureException;
import com.github.mambabosso.ewul.server.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.util.UUID;

@Slf4j
public class RoleService extends BaseDAOService<RoleDAO> {

    @Inject
    public RoleService(final RoleDAO baseDAO) {
        super(baseDAO);
    }

    public Role create(final Validator<String> name) {

        if (name == null || !name.isValid()) {
            throw new ValidationFailureException();
        }

        RoleDAO dao = getBaseDAO();
        String roleName = name.get();
        if (dao.getByName(roleName) != null) {
            throw new AlreadyExistsException();
        }
        Role role = new Role();
        role.setName(roleName);
        role.setCreatedAt(DateTime.now());
        UUID id = dao.insert(role);
        if (id != null) {
            Role result = dao.get(id);
            log.info("#create -> {}", result);
            return result;
        }
        throw new UnknownErrorException();

    }

    public Role get(final UUID id) {

        RoleDAO dao = getBaseDAO();
        Role role = dao.get(id);
        if (role != null) {
            log.info("#get -> {}", role);
            return role;
        }
        throw new NotFoundException();

    }

    public Role getByName(final String name) {

        RoleDAO dao = getBaseDAO();
        Role role = dao.getByName(name);
        if (role != null) {
            log.info("#getByName -> {}", role);
            return role;
        }
        throw new NotFoundException();

    }

    public long delete(final UUID id) {

        RoleDAO dao = getBaseDAO();
        long result = dao.delete(id);
        if (result > 0) {
            log.info("#delete -> {}", id);
            return result;
        }
        throw new NotFoundException();

    }

}

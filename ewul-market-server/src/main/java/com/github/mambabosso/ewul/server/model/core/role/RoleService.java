package com.github.mambabosso.ewul.server.model.core.role;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;
import com.github.mambabosso.ewul.server.error.Errors;
import com.github.mambabosso.ewul.server.result.Result;
import com.github.mambabosso.ewul.server.validator.Validator;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.UUID;

public class RoleService extends BaseDAOService<RoleDAO> {

    @Inject
    public RoleService(final RoleDAO baseDAO) {
        super(baseDAO);
    }

    public Result<Role> create(final Validator<String> name) {
        try {
            if (name == null || !name.isValid()) {
                return Result.failure(Errors.ROLE_VALIDATION_FAILURE.get());
            }
            RoleDAO dao = getBaseDAO();
            String roleName = name.get();
            if (dao.getByName(roleName) != null) {
                return Result.failure(Errors.ROLE_ALREADY_EXISTS.get());
            }
            Role role = new Role();
            role.setName(roleName);
            role.setCreatedAt(DateTime.now());
            UUID id = dao.insert(role);
            if (id != null) {
                return Result.success(dao.get(id));
            }
            return Result.failure(Errors.UNKNOWN_ROLE_FAILURE.get());
        } catch (PersistenceException ex) {
            return Result.failure(Errors.ROLE_PERSISTENCE_FAILURE.get(ex));
        } catch (ConstraintViolationException ex) {
            return Result.failure(Errors.ROLE_VALIDATION_FAILURE.get(ex));
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_ROLE_FAILURE.get(ex));
        }
    }

    public Result<Role> get(final UUID id) {
        try {
            RoleDAO dao = getBaseDAO();
            Role role = dao.get(id);
            if (role != null) {
                return Result.success(role);
            }
            return Result.failure(Errors.ROLE_NOT_FOUND.get());
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_ROLE_FAILURE.get(ex));
        }
    }

    public Result<Role> getByName(final String name) {
        try {
            RoleDAO dao = getBaseDAO();
            Role role = dao.getByName(name);
            if (role != null) {
                return Result.success(role);
            }
            return Result.failure(Errors.ROLE_NOT_FOUND.get());
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_ROLE_FAILURE.get(ex));
        }
    }

    public Result<Long> delete(final UUID id) {
        try {
            RoleDAO dao = getBaseDAO();
            return Result.success(dao.delete(id));
        } catch (Exception ex) {
            return Result.failure(Errors.UNKNOWN_ROLE_FAILURE.get(ex));
        }
    }

}

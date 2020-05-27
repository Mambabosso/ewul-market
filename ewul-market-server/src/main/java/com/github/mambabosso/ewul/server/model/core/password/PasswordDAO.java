package com.github.mambabosso.ewul.server.model.core.password;

import com.github.mambabosso.ewul.server.dao.BaseDAO;
import lombok.NonNull;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.UUID;

public class PasswordDAO extends BaseDAO<Password, UUID> {

    private final QPassword _password = QPassword.password;

    @Inject
    public PasswordDAO(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected UUID insert(@NonNull final Password entity) {
        return null;
    }

}

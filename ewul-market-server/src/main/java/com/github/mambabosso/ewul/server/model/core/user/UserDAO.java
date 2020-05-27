package com.github.mambabosso.ewul.server.model.core.user;

import com.github.mambabosso.ewul.server.dao.BaseDAO;
import lombok.NonNull;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.UUID;

public class UserDAO extends BaseDAO<User, UUID> {

    private final QUser _user = QUser.user;

    @Inject
    public UserDAO(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected UUID insert(@NonNull final User entity) {
        return null;
    }

}

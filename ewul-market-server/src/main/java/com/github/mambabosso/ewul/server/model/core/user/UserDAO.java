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
    public UUID insert(@NonNull final User entity) {
        return (UUID) session().save(entity);
    }

    public User get(@NonNull final UUID id) {
        return query(0, 1).select(_user).from(_user).where(_user.id.eq(id)).fetchFirst();
    }

    public User getByName(@NonNull final String name) {
        return query(0, 1).select(_user).from(_user).where(_user.name.eq(name)).fetchFirst();
    }

    public long delete(@NonNull final UUID id) {
        return delete(_user).where(_user.id.eq(id)).execute();
    }

}

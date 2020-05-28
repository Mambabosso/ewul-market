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
    public UUID insert(@NonNull final Password entity) {
        return (UUID) session().save(entity);
    }

    public Password get(@NonNull final UUID id) {
        return query(0, 1).select(_password).from(_password).where(_password.id.eq(id)).fetchFirst();
    }

    public long delete(@NonNull final UUID id) {
        return delete(_password).where(_password.id.eq(id)).execute();
    }

}

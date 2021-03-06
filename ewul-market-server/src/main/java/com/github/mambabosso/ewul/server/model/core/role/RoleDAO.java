package com.github.mambabosso.ewul.server.model.core.role;

import com.github.mambabosso.ewul.server.dao.BaseDAO;
import lombok.NonNull;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.UUID;

public class RoleDAO extends BaseDAO<Role, UUID> {

    private final QRole _role = QRole.role;

    @Inject
    public RoleDAO(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public UUID insert(@NonNull final Role entity) {
        return (UUID) session().save(entity);
    }

    public Role get(@NonNull final UUID id) {
        return query(0, 1).select(_role).from(_role).where(_role.id.eq(id)).fetchFirst();
    }

    public Role getByName(@NonNull final String name) {
        return query(0, 1).select(_role).from(_role).where(_role.name.eq(name)).fetchFirst();
    }

    public long delete(@NonNull final UUID id) {
        return delete(_role).where(_role.id.eq(id)).execute();
    }

}

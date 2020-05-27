package com.github.mambabosso.ewul.server.model.core.token;

import com.github.mambabosso.ewul.server.dao.BaseDAO;
import lombok.NonNull;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.UUID;

public class TokenDAO extends BaseDAO<Token, UUID> {

    private final QToken _token = QToken.token;

    @Inject
    public TokenDAO(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public UUID insert(@NonNull final Token entity) {
        return (UUID) session().save(entity);
    }

    public Token get(@NonNull final UUID id) {
        return query(0, 1).select(_token).from(_token).where(_token.id.eq(id)).fetchFirst();
    }

}

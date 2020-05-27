package com.github.mambabosso.ewul.server.model.core.token;

import com.github.mambabosso.ewul.server.dao.BaseDAO;
import lombok.NonNull;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.UUID;

public class TokenDAO extends BaseDAO<Token, UUID> {

    @Inject
    public TokenDAO(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected UUID insert(@NonNull final Token entity) {
        return null;
    }

}

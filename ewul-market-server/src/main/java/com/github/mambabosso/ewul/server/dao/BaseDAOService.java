package com.github.mambabosso.ewul.server.dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;

public abstract class BaseDAOService<T extends BaseDAO<?, ?>> {

    @Getter(AccessLevel.PROTECTED)
    private final T baseDAO;

    protected BaseDAOService(@NonNull final T baseDAO) {
        this.baseDAO = baseDAO;
    }

}

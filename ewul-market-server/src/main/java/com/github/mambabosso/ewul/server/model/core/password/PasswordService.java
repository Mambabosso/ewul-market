package com.github.mambabosso.ewul.server.model.core.password;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;

import javax.inject.Inject;

public class PasswordService extends BaseDAOService<PasswordDAO> {

    @Inject
    public PasswordService(final PasswordDAO baseDAO) {
        super(baseDAO);
    }

}

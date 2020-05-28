package com.github.mambabosso.ewul.server.model.core.user;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;

import javax.inject.Inject;

public class UserService extends BaseDAOService<UserDAO> {

    @Inject
    public UserService(final UserDAO baseDAO) {
        super(baseDAO);
    }

}

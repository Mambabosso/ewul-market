package com.github.mambabosso.ewul.server.model.core.role;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;

import javax.inject.Inject;

public class RoleService extends BaseDAOService<RoleDAO> {

    @Inject
    public RoleService(final RoleDAO baseDAO) {
        super(baseDAO);
    }

}

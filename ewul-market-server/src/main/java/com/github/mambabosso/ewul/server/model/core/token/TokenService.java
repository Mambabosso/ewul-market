package com.github.mambabosso.ewul.server.model.core.token;

import com.github.mambabosso.ewul.server.dao.BaseDAOService;

import javax.inject.Inject;

public class TokenService extends BaseDAOService<TokenDAO> {

    @Inject
    public TokenService(final TokenDAO baseDAO) {
        super(baseDAO);
    }

}

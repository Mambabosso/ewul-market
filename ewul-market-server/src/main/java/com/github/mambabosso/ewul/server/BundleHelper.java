package com.github.mambabosso.ewul.server;

import com.github.mambabosso.ewul.server.model.Persistable;
import com.github.mambabosso.ewul.server.model.core.password.Password;
import com.github.mambabosso.ewul.server.model.core.role.Role;
import com.github.mambabosso.ewul.server.model.core.token.Token;
import com.github.mambabosso.ewul.server.model.core.user.User;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

public final class BundleHelper {

    private BundleHelper() {
    }

    private static final Class<?>[] classes = {
            Password.class,
            Role.class,
            Token.class,
            User.class
    };

    private static HibernateBundle<EwulMarketConfiguration> hibernateBundle;

    public static synchronized HibernateBundle<EwulMarketConfiguration> getHibernateBundle() {
        if (hibernateBundle == null) {
            hibernateBundle = new HibernateBundle<EwulMarketConfiguration>(Persistable.class, classes) {
                @Override
                public PooledDataSourceFactory getDataSourceFactory(EwulMarketConfiguration configuration) {
                    return configuration.getDatabase();
                }
            };
        }
        return hibernateBundle;
    }

}

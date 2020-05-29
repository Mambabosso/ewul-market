package com.github.mambabosso.ewul.server;

import com.github.mambabosso.ewul.server.model.core.password.Password;
import com.github.mambabosso.ewul.server.model.core.role.Role;
import com.github.mambabosso.ewul.server.model.core.token.Token;
import com.github.mambabosso.ewul.server.model.core.user.User;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class EwulMarket extends Application<EwulMarketConfiguration> {

    public static void main(String[] args) throws Exception {
        new EwulMarket().run(args);
    }

    private HibernateBundle<EwulMarketConfiguration> hibernateBundle;

    private HibernateBundle<EwulMarketConfiguration> getHibernateBundle() {
        if (hibernateBundle == null) {
            hibernateBundle = new HibernateBundle<EwulMarketConfiguration>(Password.class, Role.class, Token.class, User.class) {
                @Override
                public PooledDataSourceFactory getDataSourceFactory(EwulMarketConfiguration configuration) {
                    return configuration.getDatabase();
                }
            };
        }
        return hibernateBundle;
    }

    @Override
    public void initialize(final Bootstrap<EwulMarketConfiguration> bootstrap) {
        HibernateBundle<EwulMarketConfiguration> bundle = getHibernateBundle();
        EwulMarketModule module = new EwulMarketModule(bundle);
        String basePackage = getClass().getPackage().getName();
        bootstrap.addBundle(bundle);
        bootstrap.addBundle(GuiceBundle.builder().enableAutoConfig(basePackage).modules(module).build());
    }

    @Override
    public void run(final EwulMarketConfiguration configuration, final Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/api/*");
    }

}

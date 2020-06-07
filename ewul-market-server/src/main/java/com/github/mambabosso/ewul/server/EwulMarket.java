package com.github.mambabosso.ewul.server;

import io.dropwizard.Application;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class EwulMarket extends Application<EwulMarketConfiguration> {

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            new EwulMarket().run(args);
        } else {
            throw new IllegalArgumentException("param args");
        }
    }

    @Override
    public void initialize(final Bootstrap<EwulMarketConfiguration> bootstrap) {
        HibernateBundle<EwulMarketConfiguration> bundle = BundleHelper.getHibernateBundle();
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

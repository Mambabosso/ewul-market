package com.github.mambabosso.ewul.server;

import com.github.mambabosso.ewul.server.config.JWTConfig;
import com.github.mambabosso.ewul.server.config.RegexValidationConfig;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;
import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;

import java.util.Objects;

public class EwulMarketModule extends DropwizardAwareModule<EwulMarketConfiguration> {

    private final HibernateBundle<EwulMarketConfiguration> hibernateBundle;

    public EwulMarketModule(final HibernateBundle<EwulMarketConfiguration> hibernateBundle) {
        this.hibernateBundle = Objects.requireNonNull(hibernateBundle);
    }

    @Override
    protected void configure() {
        bind(SessionFactory.class).toInstance(hibernateBundle.getSessionFactory());
        bindConfig(configuration());
    }

    private void bindConfig(EwulMarketConfiguration configuration) {
        bind(JWTConfig.class).toInstance(configuration.getJwtConfig());
        bind(RegexValidationConfig.class).toInstance(configuration.getRegexValidationConfig());
    }

}

package com.github.mambabosso.ewul.server;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class EwulMarket extends Application<EwulMarketConfiguration> {

    public static void main(String[] args) throws Exception {
        new EwulMarket().run(args);
    }

    @Override
    public void run(EwulMarketConfiguration configuration, Environment environment) throws Exception {
    }

}

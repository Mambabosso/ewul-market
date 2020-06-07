package com.github.mambabosso.ewul.server.config;

import io.dropwizard.util.Duration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTConfig {

    private String secret;

    private String issuer = "ewul";

    private Duration refreshLifetime;

    private Duration accessLifetime;

}

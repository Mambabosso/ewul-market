package com.github.mambabosso.ewul.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.mambabosso.ewul.server.config.JWTConfig;
import com.github.mambabosso.ewul.server.config.RegexValidationConfig;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class EwulMarketConfiguration extends Configuration {

    @Getter(onMethod_ = @JsonProperty("database"))
    @Setter(onMethod_ = @JsonProperty("database"))
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @Getter(onMethod_ = @JsonProperty("jwt"))
    @Setter(onMethod_ = @JsonProperty("jwt"))
    @Valid
    private JWTConfig jwtConfig = new JWTConfig();

    @Getter(onMethod_ = @JsonProperty("regexValidation"))
    @Setter(onMethod_ = @JsonProperty("regexValidation"))
    @Valid
    private RegexValidationConfig regexValidationConfig = new RegexValidationConfig();

}

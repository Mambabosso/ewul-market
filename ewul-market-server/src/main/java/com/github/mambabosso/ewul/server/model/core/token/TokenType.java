package com.github.mambabosso.ewul.server.model.core.token;

public enum TokenType {

    ID("id"),
    REFRESH("refresh"),
    ACCESS("access"),
    CUSTOM("custom");

    private final String tokenType;

    private TokenType(final String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return tokenType;
    }

}

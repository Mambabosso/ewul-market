package com.github.mambabosso.ewul.server.model.core.user;

public enum UserType {

    VISITOR("visitor"),
    USER("user"),
    VERIFIED_USER("verified_user"),
    MANAGER("manager"),
    ADMIN("admin");

    private final String userType;

    private UserType(final String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

}

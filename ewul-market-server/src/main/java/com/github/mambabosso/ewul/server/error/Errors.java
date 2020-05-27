package com.github.mambabosso.ewul.server.error;

public enum Errors {

    UNKNOWN(-1),

    UNKNOWN_PASSWORD_FAILURE(1),
    UNKNOWN_ROLE_FAILURE(2),
    UNKNOWN_TOKEN_FAILURE(3),
    UNKNOWN_USER_FAILURE(4),

    INVALID_PASSWORD(100),
    INVALID_ROLE(200),
    INVALID_TOKEN(300),
    INVALID_USER(400),

    PASSWORD_NOT_FOUND(101),
    ROLE_NOT_FOUND(201),
    TOKEN_NOT_FOUND(301),
    USER_NOT_FOUND(401),

    PASSWORD_VALIDATION_FAILURE(102),
    ROLE_VALIDATION_FAILURE(202),
    TOKEN_VALIDATION_FAILURE(302),
    USER_VALIDATION_FAILURE(402),

    PASSWORD_PERSISTENCE_FAILURE(103),
    ROLE_PERSISTENCE_FAILURE(203),
    TOKEN_PERSISTENCE_FAILURE(303),
    USER_PERSISTENCE_FAILURE(403),

    ROLE_ALREADY_EXISTS(204),
    USER_ALREADY_EXISTS(404);

    private final int code;

    private Errors(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public ErrorCode get(Exception ex) {
        return ErrorCode.create(code, ex);
    }

    public ErrorCode get() {
        return get(null);
    }

}

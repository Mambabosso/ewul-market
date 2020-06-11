package com.github.mambabosso.ewul.server.error;

public class InvalidTokenException extends EwulException {

    public static final int ERROR_CODE = 4000;

    public InvalidTokenException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    public InvalidTokenException() {
        super(ERROR_CODE);
    }

}

package com.github.mambabosso.ewul.server.error;

public class ValidationFailureException extends EwulException {

    public static final int ERROR_CODE = 1000;

    public ValidationFailureException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    public ValidationFailureException() {
        super(ERROR_CODE);
    }

}

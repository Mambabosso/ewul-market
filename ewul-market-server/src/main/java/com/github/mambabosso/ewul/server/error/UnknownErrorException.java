package com.github.mambabosso.ewul.server.error;

public class UnknownErrorException extends EwulException {

    public static final int ERROR_CODE = -1;

    public UnknownErrorException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    public UnknownErrorException() {
        super(ERROR_CODE);
    }

}

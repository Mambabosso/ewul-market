package com.github.mambabosso.ewul.server.error;

public class AlreadyExistsException extends EwulException {

    public static final int ERROR_CODE = 2000;

    public AlreadyExistsException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    public AlreadyExistsException() {
        super(ERROR_CODE);
    }

}

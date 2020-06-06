package com.github.mambabosso.ewul.server.error;

public class NotFoundException extends EwulException {

    public static final int ERROR_CODE = 3000;

    public NotFoundException(Throwable cause) {
        super(ERROR_CODE, cause);
    }

    public NotFoundException() {
        super(ERROR_CODE);
    }

}

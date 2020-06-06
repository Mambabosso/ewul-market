package com.github.mambabosso.ewul.server.error;

public abstract class EwulException extends RuntimeException {

    private final int code;

    public EwulException(final int code, final Throwable cause) {
        super(String.format("error.%d", code), cause);
        this.code = code;
    }

    public EwulException(final int code) {
        super(String.format("error.%d", code));
        this.code = code;
    }

    public int getErrorCode() {
        return code;
    }

}

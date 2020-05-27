package com.github.mambabosso.ewul.server.error;

import lombok.Data;

import java.io.Serializable;

@Data
public final class ErrorCode implements Serializable {

    private final int code;
    private final Exception exception;

    private ErrorCode(final int code, final Exception exception) {
        this.code = code;
        this.exception = exception;
    }

    public static ErrorCode create(final int code, final Exception exception) {
        if (code == 0) {
            throw new IllegalArgumentException("code can not be zero");
        }
        return new ErrorCode(code, exception);
    }

    public static ErrorCode create(final int code) {
        return create(code, null);
    }

}

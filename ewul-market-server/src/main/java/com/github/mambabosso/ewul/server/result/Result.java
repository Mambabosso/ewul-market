package com.github.mambabosso.ewul.server.result;

import com.github.mambabosso.ewul.server.error.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@EqualsAndHashCode
public final class Result<T> {

    private boolean success;
    private T value;
    private ErrorCode error;

    private Result() {
    }

    public boolean isSuccess() {
        return success;
    }

    public T get() {
        return value;
    }

    public T getOrThrow() throws Exception {
        if (success) {
            return value;
        }
        throw error.getException();
    }

    public ErrorCode getError() {
        return error;
    }

    public Result<T> removeValue() {
        this.value = null;
        return this;
    }

    public <X> X unwrap(@NonNull final Function<T, X> function) throws Exception {
        return function.apply(getOrThrow());
    }

    public Optional<T> optional() {
        return Optional.ofNullable(value);
    }

    public Map<String, Object> map() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        if (success) {
            map.put("value", value);
        } else {
            map.put("error", error);
        }
        return map;
    }

    public <X> Result<X> byError() {
        if (success) {
            throw new IllegalStateException();
        }
        return Result.failure(error);
    }

    public static <T> Result<T> success(@NonNull final T resultValue) {
        Result<T> result = new Result<>();
        result.success = true;
        result.value = resultValue;
        return result;
    }

    public static <T> Result<T> failure(final ErrorCode errorCode) {
        Result<T> result = new Result<>();
        result.success = false;
        result.error = (errorCode != null) ? errorCode : ErrorCode.create(-1);
        return result;
    }

}

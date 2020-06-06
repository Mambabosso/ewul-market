package com.github.mambabosso.ewul.server.result;

import com.github.mambabosso.ewul.server.error.EwulException;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@EqualsAndHashCode
public final class Result<T> {

    private boolean success;
    private T value;
    private EwulException error;

    private Result() {
    }

    public boolean isSuccess() {
        return success;
    }

    public T get() {
        return value;
    }

    public EwulException getError() {
        return error;
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
            map.put("error", error.getErrorCode());
        }
        return map;
    }

    public Response toResponse(final int successStatusCode, final int errorStatusCode) {
        return Response.status(success ? successStatusCode : errorStatusCode).entity(map()).build();
    }

    public static <T> Result<T> success(@NonNull final T resultValue) {
        Result<T> result = new Result<>();
        result.success = true;
        result.value = resultValue;
        return result;
    }

    public static <T> Result<T> failure(@NonNull final EwulException error) {
        Result<T> result = new Result<>();
        result.success = false;
        result.error = error;
        return result;
    }

}

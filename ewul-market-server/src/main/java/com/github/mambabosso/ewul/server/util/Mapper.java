package com.github.mambabosso.ewul.server.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

import java.util.Map;

public final class Mapper {

    private Mapper() {
    }

    private static ObjectMapper objectMapper;

    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static <T> Map<String, Object> toMap(@NonNull final T value) {
        return getObjectMapper().convertValue(value, new TypeReference<Map<String, Object>>() {});
    }

    public static <T> T fromMap(@NonNull final Map<String, Object> value, @NonNull final Class<T> tClass) {
        return getObjectMapper().convertValue(value, tClass);
    }

}

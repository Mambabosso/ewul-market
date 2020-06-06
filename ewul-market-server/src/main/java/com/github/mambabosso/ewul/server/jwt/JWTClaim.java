package com.github.mambabosso.ewul.server.jwt;

import com.github.mambabosso.ewul.server.util.Mapper;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Map;

@EqualsAndHashCode
public final class JWTClaim<T extends Serializable> {

    private final String name;
    private final T value;

    private JWTClaim(final String name, final T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public Map<String, Object> toMap() {
        return Mapper.toMap(value);
    }

    public static <T extends Serializable> JWTClaim<T> build(@NonNull final String name, @NonNull final T value) {
        return new JWTClaim<>(name, value);
    }

}

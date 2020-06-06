package com.github.mambabosso.ewul.server.validation;

public interface Validator<T> {

    public T get();

    public default boolean isValid() {
        return true;
    }

}

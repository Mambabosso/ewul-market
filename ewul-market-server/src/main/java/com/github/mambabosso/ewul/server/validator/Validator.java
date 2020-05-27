package com.github.mambabosso.ewul.server.validator;

public interface Validator<T> {

    public T get();

    public default boolean isValid() {
        return true;
    }

}

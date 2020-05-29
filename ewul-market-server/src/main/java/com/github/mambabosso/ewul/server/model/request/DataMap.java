package com.github.mambabosso.ewul.server.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor(onConstructor_ = @JsonCreator)
@NoArgsConstructor
public class DataMap implements Serializable {

    private static ObjectMapper mapper;

    private static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
        return mapper;
    }

    public static <T> T convert(@NonNull final Object obj, @NonNull final Class<T> convertTo) {
        return getMapper().convertValue(obj, convertTo);
    }

    public static <T> T convert(@NonNull final Object obj, @NonNull final TypeReference<T> convertTo) {
        return getMapper().convertValue(obj, convertTo);
    }

    private Map<String, Object> values;

    public Object get(final String key) {
        if (values != null && values.containsKey(key)) {
            return values.get(key);
        }
        return null;
    }

    public <T> T get(final String key, @NonNull final Class<T> convertTo) {
        return convert(get(key), convertTo);
    }

    public <T> T get(final String key, @NonNull final TypeReference<T> convertTo) {
        return convert(get(key), convertTo);
    }

    public String getString(final String key) {
        return get(key, String.class);
    }

    public int getInt(final String key) {
        return get(key, Integer.class);
    }

}

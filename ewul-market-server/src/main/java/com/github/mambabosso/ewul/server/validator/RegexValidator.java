package com.github.mambabosso.ewul.server.validator;

import lombok.*;

import java.io.Serializable;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegexValidator implements Validator<String>, Serializable {

    private String value;

    private String regex;

    @Override
    public String get() {
        if (value == null) {
            throw new NullPointerException("value");
        }
        return value;
    }

    @Override
    public boolean isValid() {
        if (regex == null) {
            throw new NullPointerException("regex");
        }
        return Pattern.compile(regex).matcher(get()).matches();
    }

}

package com.github.mambabosso.ewul.server.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegexValidationConfig {

    private String username = "^.{2,50}$";

    private String password = "^.{4,50}$";

    private String rolename = "^.{2,50}$";

}

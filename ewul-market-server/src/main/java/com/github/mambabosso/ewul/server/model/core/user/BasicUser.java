package com.github.mambabosso.ewul.server.model.core.user;

import com.github.mambabosso.ewul.server.model.core.role.Role;
import lombok.*;

import java.io.Serializable;
import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasicUser implements Principal, Serializable {

    private String name;
    private UserType type;
    private @Singular Set<String> roles;

    public static BasicUser from(@NonNull final User user) {
        String userName = user.getName();
        UserType userType = user.getType();
        Set<Role> userRoles = user.getRoles();
        if (userRoles != null) {
            Set<String> roles = userRoles.stream().map(Role::getName).collect(Collectors.toSet());
            return BasicUser.builder().name(userName).type(userType).roles(roles).build();
        } else {
            return BasicUser.builder().name(userName).type(userType).build();
        }
    }

}

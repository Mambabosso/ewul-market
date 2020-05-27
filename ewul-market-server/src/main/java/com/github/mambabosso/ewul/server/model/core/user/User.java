package com.github.mambabosso.ewul.server.model.core.user;

import com.github.mambabosso.ewul.server.model.Persistable;
import com.github.mambabosso.ewul.server.model.core.password.Password;
import com.github.mambabosso.ewul.server.model.core.role.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.security.Principal;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User implements Principal, Persistable<UUID> {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "user_id", updatable = false)
    private UUID id;

    @NotNull
    @Pattern(regexp = "^.{1,50}$", message = "invalid user name")
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password", referencedColumnName = "password_id")
    private Password password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", updatable = false)
    private UserType type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "role"))
    private Set<Role> roles;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private DateTime createdAt;

    @NotNull
    @Column(name = "locked")
    private boolean locked;

    public User(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}

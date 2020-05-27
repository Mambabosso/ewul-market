package com.github.mambabosso.ewul.server.model.core.role;

import com.github.mambabosso.ewul.server.model.Persistable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "role")
public final class Role implements Persistable<UUID> {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "role_id", updatable = false)
    private UUID id;

    @NotNull
    @Pattern(regexp = "^.{1,50}$", message = "invalid role name")
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private DateTime createdAt;

    @NotNull
    @Column(name = "locked")
    private boolean locked;

    public Role(final String name) {
        this.name = name;
    }

}

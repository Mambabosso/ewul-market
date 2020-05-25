package com.github.mambabosso.ewul.server.model.password;

import com.github.mambabosso.ewul.server.model.Persistable;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "password")
public final class Password implements Persistable<UUID> {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "password_id", updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "hash", updatable = false)
    private String hash;

    @NotNull
    @Column(name = "last_access")
    private DateTime lastAccess;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private DateTime createdAt;

    @NotNull
    @Column(name = "locked")
    private boolean locked;

}

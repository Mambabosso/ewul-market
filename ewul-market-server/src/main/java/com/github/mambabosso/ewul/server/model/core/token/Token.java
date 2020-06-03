package com.github.mambabosso.ewul.server.model.core.token;

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
@Table(name = "token")
public class Token implements Persistable<UUID> {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "token_id", updatable = false)
    private UUID id;

    @NotNull
    @Column(name = "value", unique = true, updatable = false)
    private String value;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", updatable = false)
    private TokenType type;

    @NotNull
    @Column(name = "expires_at", updatable = false)
    private DateTime expiresAt;

    @NotNull
    @Column(name = "last_access")
    private DateTime lastAccess;

    @NotNull
    @Column(name = "created_at", updatable = false)
    private DateTime createdAt;

    @NotNull
    @Column(name = "locked")
    private boolean locked;

    public Token(final String value) {
        this.value = value;
    }

}

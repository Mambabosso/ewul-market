package com.github.mambabosso.ewul.server.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.Objects;

public final class JWTHandler {

    private final String issuer;
    private final Algorithm algorithm;

    public JWTHandler(final String issuer, final Algorithm algorithm) {
        this.issuer = Objects.requireNonNull(issuer);
        this.algorithm = Objects.requireNonNull(algorithm);
    }

    public String encode(final Duration lifetime, final JWTClaim<?> ...claims) {

        if (lifetime == null) {
            throw new IllegalArgumentException("lifetime");
        }

        DateTime now = DateTime.now();
        DateTime expiresAt = now.plus(lifetime);

        JWTCreator.Builder builder = JWT.create();
        builder.withIssuer(issuer);
        builder.withIssuedAt(now.toDate());
        builder.withExpiresAt(expiresAt.toDate());

        for (JWTClaim<?> claim : claims) {
            Object value = claim.getValue();
            if (value instanceof String) {
                builder.withClaim(claim.getName(), (String) value);
            } else {
                builder.withClaim(claim.getName(), claim.toMap());
            }
        }

        return builder.sign(algorithm);

    }

    public DecodedJWT decode(final String token) {
        try {
            Verification verification = JWT.require(algorithm);
            verification.withIssuer(issuer);
            return verification.build().verify(token);
        } catch (Exception ex) {
            return null;
        }
    }

    public <T> T decode(final String token, final String claim, final Class<T> tClass) {
        try {
            return Objects.requireNonNull(decode(token)).getClaim(claim).as(tClass);
        } catch (Exception ex) {
            return null;
        }
    }

}

package com.github.mambabosso.ewul.server.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.Objects;

public class JWTHelper {

    private final Algorithm algorithm;

    public JWTHelper(final Algorithm algorithm) {
        this.algorithm = Objects.requireNonNull(algorithm);
    }

    public DecodedJWT decode(final String token, final String issuer) {
        try {
            Verification verification = JWT.require(algorithm);
            verification.withIssuer(issuer);
            return verification.build().verify(token);
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean isValid(final String token, final String issuer) {
        return decode(token, issuer) != null;
    }

    public String generate(final String issuer, final Duration lifetime, final JWTClaim<?> ...claims) {

        DateTime now = DateTime.now();
        DateTime expiresAt = now.plus(lifetime);

        JWTCreator.Builder builder = JWT.create();
        builder.withIssuer(issuer);
        builder.withIssuedAt(now.toDate());
        builder.withExpiresAt(expiresAt.toDate());

        for (JWTClaim<?> claim : claims) {
            builder.withClaim(claim.getName(), claim.toMap());
        }

        return builder.sign(algorithm);
    }

    public <T> T getClaimValue(final DecodedJWT jwt, final String claim, final Class<T> tClass) {
        return jwt.getClaim(claim).as(tClass);
    }

}

package com.github.mambabosso.ewul.server.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.mambabosso.ewul.server.model.core.user.SimpleUser;
import com.github.mambabosso.ewul.server.model.core.user.UserType;
import org.joda.time.Duration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JWTHandlerTest {

    private static final Algorithm ALGORITHM = Algorithm.HMAC256("654321");
    private static final Algorithm ALGORITHM2 = Algorithm.HMAC256("123456");

    private static final JWTHandler HANDLER = new JWTHandler("issuer", ALGORITHM);
    private static final JWTHandler HANDLER2 = new JWTHandler("issuer", ALGORITHM2);

    @Test
    public void encode_decode_test() {

        SimpleUser user = SimpleUser.builder().name("user1").type(UserType.USER).role("r1").role("r2").build();

        String jwt = HANDLER.encode(Duration.standardDays(1), JWTClaim.build("user", user));
        String jwt2 = HANDLER2.encode(Duration.standardDays(1), JWTClaim.build("user", user));

        assertNotNull(jwt);
        assertNotNull(jwt2);

        assertNotEquals(jwt, jwt2);

        DecodedJWT decodedJWT = HANDLER.decode(jwt);
        DecodedJWT decodedJWT2 = HANDLER2.decode(jwt2);

        assertNotNull(decodedJWT);
        assertNotNull(decodedJWT2);

        assertNull(HANDLER.decode(jwt2));
        assertNull(HANDLER2.decode(jwt));

        SimpleUser decUser = HANDLER.decode(jwt, "user", SimpleUser.class);
        assertEquals("user1", decUser.getName());
        assertEquals(UserType.USER, decUser.getType());
        assertEquals(2, decUser.getRoles().size());

        assertNull(HANDLER.decode(jwt, "user2", SimpleUser.class));

    }

}

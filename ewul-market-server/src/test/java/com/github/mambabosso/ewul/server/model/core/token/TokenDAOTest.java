package com.github.mambabosso.ewul.server.model.core.token;

import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.joda.time.DateTime;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(DropwizardExtensionsSupport.class)
public class TokenDAOTest {

    private static final DAOTestExtension daoTestRule = DAOTestExtension.newBuilder()
            .addEntityClass(Token.class)
            .build();

    private static TokenDAO dao;

    private static UUID id1;
    private static UUID id2;

    @BeforeAll
    public static void init() {
        dao = new TokenDAO(daoTestRule.getSessionFactory());
    }

    @Order(1)
    @Test
    public void insert() {

        DateTime now = DateTime.now();

        id1 = daoTestRule.inTransaction(() -> {
            return dao.insert(Token.builder().value("token1").type(TokenType.ID).lastAccess(now).expiresAt(now).createdAt(now).build());
        });

        id2 = daoTestRule.inTransaction(() -> {
            return dao.insert(Token.builder().value("token2").type(TokenType.ID).lastAccess(now).expiresAt(now).createdAt(now).build());
        });

        assertNotNull(id1);
        assertNotNull(id2);

    }

    @Order(2)
    @Test
    public void get() {

        assertEquals("token1", daoTestRule.inTransaction(() -> dao.get(id1).getValue()));
        assertEquals("token2", daoTestRule.inTransaction(() -> dao.get(id2).getValue()));

    }

}

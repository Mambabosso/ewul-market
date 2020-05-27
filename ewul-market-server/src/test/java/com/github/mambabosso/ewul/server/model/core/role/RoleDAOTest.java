package com.github.mambabosso.ewul.server.model.core.role;

import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.joda.time.DateTime;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(DropwizardExtensionsSupport.class)
public class RoleDAOTest {

    private static final DAOTestExtension daoTestRule = DAOTestExtension.newBuilder()
            .addEntityClass(Role.class)
            .build();

    private static RoleDAO dao;

    private static UUID id1;
    private static UUID id2;

    @BeforeAll
    public static void init() {
        dao = new RoleDAO(daoTestRule.getSessionFactory());
    }

    @Order(1)
    @Test
    public void insert() {

        DateTime now = DateTime.now();

        id1 = daoTestRule.inTransaction(() -> {
            return dao.insert(Role.builder().name("role1").createdAt(now).build());
        });

        id2 = daoTestRule.inTransaction(() -> {
            return dao.insert(Role.builder().name("role2").createdAt(now).build());
        });

        assertNotNull(id1);
        assertNotNull(id2);

    }

    @Order(2)
    @Test
    public void get() {

        assertEquals("role1", daoTestRule.inTransaction(() -> dao.get(id1).getName()));
        assertEquals("role2", daoTestRule.inTransaction(() -> dao.get(id2).getName()));

    }

}

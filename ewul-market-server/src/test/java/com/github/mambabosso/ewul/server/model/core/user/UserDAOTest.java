package com.github.mambabosso.ewul.server.model.core.user;

import com.github.mambabosso.ewul.server.model.core.password.Password;
import com.github.mambabosso.ewul.server.model.core.role.Role;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.joda.time.DateTime;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(DropwizardExtensionsSupport.class)
public class UserDAOTest {

    private static final DAOTestExtension daoTestRule = DAOTestExtension.newBuilder()
            .addEntityClass(User.class)
            .addEntityClass(Password.class)
            .addEntityClass(Role.class)
            .build();

    private static UserDAO dao;

    private static UUID id1;
    private static UUID id2;

    @BeforeAll
    public static void init() {
        dao = new UserDAO(daoTestRule.getSessionFactory());
    }

    @Order(1)
    @Test
    public void insert() {

        DateTime now = DateTime.now();

        Password p1 = Password.builder().hash("hash1").lastAccess(now).createdAt(now).build();
        Password p2 = Password.builder().hash("hash1").lastAccess(now).createdAt(now).build();

        id1 = daoTestRule.inTransaction(() -> {
            return dao.insert(User.builder().name("user1").type(UserType.USER).password(p1).createdAt(now).build());
        });

        id2 = daoTestRule.inTransaction(() -> {
            return dao.insert(User.builder().name("user2").type(UserType.USER).password(p2).createdAt(now).build());
        });

        assertNotNull(id1);
        assertNotNull(id2);

    }

    @Order(2)
    @Test
    public void get() {

        assertEquals("user1", daoTestRule.inTransaction(() -> dao.get(id1).getName()));
        assertEquals("user2", daoTestRule.inTransaction(() -> dao.get(id2).getName()));

    }

}

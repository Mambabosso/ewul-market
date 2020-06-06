package com.github.mambabosso.ewul.server;

import com.github.mambabosso.ewul.server.model.core.password.Password;
import com.github.mambabosso.ewul.server.model.core.role.Role;
import com.github.mambabosso.ewul.server.model.core.token.Token;
import com.github.mambabosso.ewul.server.model.core.user.User;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(DropwizardExtensionsSupport.class)
public class UniversalTest {

    private static final DAOTestExtension daoTestRule = DAOTestExtension.newBuilder()
            .addEntityClass(Password.class)
            .addEntityClass(Role.class)
            .addEntityClass(Token.class)
            .addEntityClass(User.class)
            .build();

    @BeforeAll
    public static void init() {
    }

    @Order(1)
    @Test
    public void test1() {

    }

    @Order(2)
    @Test
    public void test2() {

    }

}

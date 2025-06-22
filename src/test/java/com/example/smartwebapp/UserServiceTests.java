package com.example.smartwebapp;

import com.example.smartwebapp.model.User;
import com.example.smartwebapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Pruebas de integración para {@link UserService}.
 * <p>
 * Nota: este test requiere una base de datos configurada y el bean de servicio
 * no se carga de forma automática en @DataJpaTest. Podría mejorarse usando
 * {@code @SpringBootTest} o importando el servicio explícitamente.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(UserService.class)
public class UserServiceTests {

    /** Servicio de usuarios inyectado para las pruebas. */
    @Autowired
    private UserService userService;

    @Test
    void testSaveUser() {
        // Arrange: se crea un usuario con valores mínimos
        User user = new User();
        user.setName("Test");

        // Act: se guarda la entidad usando el servicio
        User saved = userService.save(user);

        // Assert: la operación asigna un identificador
        assertThat(saved.getId()).isNotNull();
    }
}

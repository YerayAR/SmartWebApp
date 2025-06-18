package com.example.smartwebapp;

import com.example.smartwebapp.model.User;
import com.example.smartwebapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void testSaveUser() {
        User user = new User();
        user.setName("Test");
        User saved = userService.save(user);
        assertThat(saved.getId()).isNotNull();
    }
}

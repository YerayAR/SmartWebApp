package com.example.smartwebapp.controller;

import com.example.smartwebapp.model.User;
import com.example.smartwebapp.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

/**
 * Managed Bean JSF para la gestión de usuarios.
 */
@Named("userBean")
@RequestScoped
public class UserBean implements Serializable {

    @Inject
    private UserService userService;

    private User user = new User();
    private List<User> users;

    @PostConstruct
    public void init() {
        loadUsers();
    }

    public void save() {
        try {
            if (user.getName() != null && !user.getName().trim().isEmpty()) {
                userService.save(user);
                user = new User(); // Reset form
                loadUsers(); // Reload list
            }
        } catch (Exception e) {
            // Handle error
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        users = userService.findAll();
    }

    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}


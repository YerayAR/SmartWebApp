package com.example.smartwebapp.config;

import com.example.smartwebapp.service.TaskService;
import com.example.smartwebapp.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Producer CDI para hacer disponibles los servicios de Spring en contexto CDI.
 */
@Component
@ApplicationScoped
public class SpringCdiProducer {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Produces
    @ApplicationScoped
    public UserService produceUserService() {
        return userService;
    }

    @Produces
    @ApplicationScoped
    public TaskService produceTaskService() {
        return taskService;
    }
}


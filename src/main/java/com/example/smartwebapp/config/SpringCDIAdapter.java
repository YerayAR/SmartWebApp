package com.example.smartwebapp.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import jakarta.servlet.ServletContext;
import jakarta.inject.Inject;

import com.example.smartwebapp.service.UserService;
import com.example.smartwebapp.service.TaskService;

/**
 * Adaptador para exponer beans de Spring como beans CDI
 */
@ApplicationScoped
public class SpringCDIAdapter {

    @Inject
    private ServletContext servletContext;

    private ApplicationContext getApplicationContext() {
        return WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }

    @Produces
    @Named("userService")
    @ApplicationScoped
    public UserService getUserService() {
        return getApplicationContext().getBean(UserService.class);
    }

    @Produces
    @Named("taskService") 
    @ApplicationScoped
    public TaskService getTaskService() {
        return getApplicationContext().getBean(TaskService.class);
    }
}


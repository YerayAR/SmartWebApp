package com.example.smartwebapp.config;

import jakarta.faces.webapp.FacesServlet;
import org.apache.myfaces.webapp.ConfigureListener;
import org.jboss.weld.environment.servlet.Listener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsfConfig {

    @Bean
    public ServletRegistrationBean<FacesServlet> facesServlet() {
        ServletRegistrationBean<FacesServlet> servlet =
                new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
        servlet.setLoadOnStartup(1);
        return servlet;
    }

    @Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<>(new ConfigureListener());
    }

    @Bean
    public ServletListenerRegistrationBean<Listener> weldListener() {
        return new ServletListenerRegistrationBean<>(new Listener());
    }

    @Bean
    public ServletContextInitializer jsfInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("jakarta.faces.PROJECT_STAGE", "Development");
            servletContext.setInitParameter("jakarta.faces.FACELETS_REFRESH_PERIOD", "0");
        };
    }
}

package com.example.smartwebapp.config;

import jakarta.faces.webapp.FacesServlet;
import org.apache.myfaces.webapp.StartupServletContextListener;
import org.jboss.weld.environment.servlet.Listener;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

@Configuration
public class JsfInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("jakarta.faces.PROJECT_STAGE", "Development");
        servletContext.setInitParameter("jakarta.faces.FACELETS_REFRESH_PERIOD", "0");
        servletContext.setInitParameter("jakarta.faces.DEFAULT_SUFFIX", ".xhtml");
        servletContext.setInitParameter("jakarta.faces.WEBAPP_RESOURCES_DIRECTORY", "/resources");
    }

    @Bean
    public ServletRegistrationBean<FacesServlet> facesServlet() {
        ServletRegistrationBean<FacesServlet> bean = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
        bean.setLoadOnStartup(1);
        bean.setName("FacesServlet");
        return bean;
    }

    @Bean
    public ServletListenerRegistrationBean<StartupServletContextListener> startupListener() {
        return new ServletListenerRegistrationBean<>(new StartupServletContextListener());
    }

    @Bean
    public ServletListenerRegistrationBean<Listener> weldListener() {
        return new ServletListenerRegistrationBean<>(new Listener());
    }
}

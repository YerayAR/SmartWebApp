package com.example.smartwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada principal de la aplicación Spring Boot.
 * <p>
 * La anotación {@link SpringBootApplication} habilita la configuración automática
 * y el escaneo de componentes dentro del paquete actual.
 */
@SpringBootApplication
public class SmartWebAppApplication {

    /**
     * Ejecuta la aplicación.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        SpringApplication.run(SmartWebAppApplication.class, args);
    }
}

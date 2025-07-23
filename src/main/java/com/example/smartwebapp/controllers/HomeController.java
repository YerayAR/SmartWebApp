package com.example.smartwebapp.controllers;
/*
 * Controlador inicial que redirige la raiz a la vista principal.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador que redirige la raiz de la aplicacion a la pagina principal.
 */
@Controller
public class HomeController {

    /**
     * Atiende la ruta `/` para mostrar la vista inicial.
     *
     * @return nombre de la plantilla principal
     */
    @GetMapping({"/", ""})
    public String home() {
        // Retorna la vista Thymeleaf de inicio
        return "index";
    }
}

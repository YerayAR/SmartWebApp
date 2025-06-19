package com.example.smartwebapp.controller;

import com.example.smartwebapp.model.User;
import com.example.smartwebapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador web para la gestión de usuarios.
 * <p>
 * Atiende la ruta <code>/users</code> y muestra vistas JSF para listar
 * y registrar nuevos usuarios en la base de datos.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    /** Servicio de acceso a usuarios. */
    private final UserService userService;

    /**
     * Constructor por inyección de dependencias.
     *
     * @param userService servicio de usuarios
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Muestra la lista de usuarios registrados.
     *
     * @param model modelo de vista
     * @return nombre de la plantilla JSF
     */
    @GetMapping
    public String list(Model model) {
        return prepareListView(model);
    }

    /**
     * Procesa el formulario de alta de usuario.
     *
     * @param user   entidad recibida del formulario
     * @param result resultado de la validación
     * @param model  modelo de vista
     * @return redirección a la lista de usuarios o vista con errores
     */
    @PostMapping
    public String save(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        // En caso de errores de validación se vuelve a mostrar la vista con
        // la lista de usuarios recargada.
        if (result.hasErrors()) {
            // Si hay errores de validación se muestra nuevamente la vista
            // recargando la lista de usuarios.
            return prepareListView(model);
        }
        // Guardamos el nuevo usuario y redirigimos a la lista principal
        userService.save(user);
        return "redirect:/users";
    }

    /**
     * Prepara el modelo con la lista de usuarios y un formulario vacío.
     * Este método evita duplicar la recarga de datos tanto en GET como en POST.
     *
     * @param model modelo de vista
     * @return nombre de la plantilla
     */
    private String prepareListView(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "users.xhtml";
    }
}

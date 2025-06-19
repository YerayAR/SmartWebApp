package com.example.smartwebapp.controller;

import com.example.smartwebapp.model.Task;
import com.example.smartwebapp.model.User;
import com.example.smartwebapp.service.TaskService;
import com.example.smartwebapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para la gestión de tareas.
 * <p>
 * Atiende las peticiones bajo la ruta <code>/tasks</code> y renderiza
 * vistas JSF para listar y crear tareas asociadas a un usuario.
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {

    /** Servicio de tareas. */
    private final TaskService taskService;
    /** Servicio de usuarios para poblar el selector. */
    private final UserService userService;

    /**
     * Constructor que recibe dependencias por inyección.
     *
     * @param taskService servicio de tareas
     * @param userService servicio de usuarios
     */
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    /**
     * Muestra la lista de tareas existentes.
     *
     * @param model modelo de vista
     * @return nombre de la plantilla JSF
     */
    @GetMapping
    public String list(Model model) {
        return prepareListView(model);
    }

    /**
     * Procesa el alta de una nueva tarea.
     *
     * @param task   tarea a persistir
     * @param result resultado de la validación
     * @param model  modelo de vista
     * @return redirección a la lista o vista con errores
     */
    @PostMapping
    public String save(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model) {
        // Si la validación detecta errores se regresa a la misma vista
        // poblando nuevamente los datos necesarios para su renderizado.
        if (result.hasErrors()) {
            // Si la validación falla se vuelve a la vista con los datos necesarios
            return prepareListView(model);
        }
        // Persistimos la tarea y redirigimos para evitar reenvío del formulario
        taskService.save(task);
        return "redirect:/tasks";
    }

    /**
     * Carga en el modelo los datos necesarios para la vista de tareas.
     * Permite reutilizar la misma lógica desde varios métodos.
     *
     * @param model modelo de vista
     * @return nombre de la plantilla
     */
    private String prepareListView(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("task", new Task());
        model.addAttribute("users", userService.findAll());
        return "tasks.xhtml";
    }
}

package com.example.smartwebapp.controller;

import com.example.smartwebapp.model.Task;
import com.example.smartwebapp.model.User;
import com.example.smartwebapp.service.TaskService;
import com.example.smartwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Controlador Spring MVC para la gestión de tareas.
 */
@Controller
@RequestMapping("/tasks")
public class TaskWebController {

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserService userService;

    /**
     * Muestra la página principal de tareas con el formulario de alta y la
     * lista existente.
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("task", new Task());
        return "tasks/index";
    }

    /**
     * Procesa el formulario de creación de una tarea.
     *
     * @param task   entidad enviada desde el formulario
     * @param userId identificador del usuario asociado
     * @param result resultado de la validación
     * @param model  modelo de la vista
     */
    @PostMapping
    public String create(@Valid @ModelAttribute Task task,
                        @RequestParam("userId") Long userId,
                        BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tasks", taskService.findAll());
            model.addAttribute("users", userService.findAll());
            return "tasks/index";
        }
        
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            task.setUser(userOptional.get());
            taskService.save(task);
        }
        
        return "redirect:/tasks";
    }

    // --- API REST endpoints ---
    /**
     * Devuelve todas las tareas en formato JSON.
     */
    @GetMapping("/api")
    @ResponseBody
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    /**
     * Obtiene una tarea por su identificador.
     */
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.findByIdOptional(id);
        return task.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea una tarea a través de la API REST.
     */
    @PostMapping("/api")
    @ResponseBody
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.save(task);
    }

    /**
     * Elimina una tarea por su identificador.
     */
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.findByIdOptional(id).isPresent()) {
            taskService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Cambia el estado de completado de una tarea.
     */
    @PostMapping("/api/{id}/toggle")
    @ResponseBody
    public ResponseEntity<Task> toggleTask(@PathVariable Long id) {
        Optional<Task> taskOptional = taskService.findByIdOptional(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setCompleted(!task.isCompleted());
            Task updatedTask = taskService.save(task);
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }
}


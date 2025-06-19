package com.example.smartwebapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * Entidad que representa a un usuario del sistema.
 */
@Entity
public class User {

    /** Identificador autogenerado. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre del usuario. */
    @NotBlank
    private String name;

    /**
     * Lista de tareas asociadas al usuario.
     * Al usar {@code CascadeType.ALL} las tareas se persisten y eliminan
     * junto con el usuario.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;

    // getters and setters
    /** @return identificador del usuario */
    public Long getId() { return id; }
    /** @param id identificador a asignar */
    public void setId(Long id) { this.id = id; }

    /** @return nombre del usuario */
    public String getName() { return name; }
    /** @param name nuevo nombre */
    public void setName(String name) { this.name = name; }

    /** @return tareas asociadas */
    public List<Task> getTasks() { return tasks; }
    /** @param tasks lista de tareas */
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }
}

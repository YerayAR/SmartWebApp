package com.example.smartwebapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Entidad que representa una tarea asignada a un usuario.
 */
@Entity
public class Task {

    /** Identificador autogenerado de la tarea. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Descripción de la tarea. */
    @NotBlank
    private String description;

    /**
     * Usuario al que se asigna la tarea.
     * Se mantiene una relación muchos-a-uno porque un usuario
     * puede tener varias tareas asociadas.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // getters y setters
    /** @return id de la tarea */
    public Long getId() { return id; }
    /** @param id nuevo identificador */
    public void setId(Long id) { this.id = id; }

    /** @return descripción de la tarea */
    public String getDescription() { return description; }
    /** @param description nueva descripción */
    public void setDescription(String description) { this.description = description; }

    /** @return usuario asignado */
    public User getUser() { return user; }
    /** @param user usuario asignado */
    public void setUser(User user) { this.user = user; }
}

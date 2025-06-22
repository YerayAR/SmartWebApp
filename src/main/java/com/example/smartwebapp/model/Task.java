package com.example.smartwebapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    /** Prioridad de la tarea. */
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;

    /** Estado de completado de la tarea. */
    private boolean completed = false;

    /** Fecha de creación de la tarea. */
    @CreationTimestamp
    private LocalDateTime createdAt;

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

    /** @return prioridad de la tarea */
    public Priority getPriority() { return priority; }
    /** @param priority nueva prioridad */
    public void setPriority(Priority priority) { this.priority = priority; }

    /** @return si la tarea está completada */
    public boolean isCompleted() { return completed; }
    /** @param completed nuevo estado de completado */
    public void setCompleted(boolean completed) { this.completed = completed; }

    /** @return fecha de creación */
    public LocalDateTime getCreatedAt() { return createdAt; }
    /** @param createdAt nueva fecha de creación */
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

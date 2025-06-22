package com.example.smartwebapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa a un usuario del sistema.
 */
@Entity
@Table(name = "app_user")
public class User {

    /** Identificador autogenerado. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nombre del usuario. */
    @NotBlank
    private String name;

    /** Email del usuario. */
    @Email
    @NotBlank
    private String email;

    /** Fecha de creación del usuario. */
    @CreationTimestamp
    private LocalDateTime createdAt;

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

    /** @return email del usuario */
    public String getEmail() { return email; }
    /** @param email nuevo email */
    public void setEmail(String email) { this.email = email; }

    /** @return fecha de creación */
    public LocalDateTime getCreatedAt() { return createdAt; }
    /** @param createdAt nueva fecha de creación */
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    /** @return tareas asociadas */
    public List<Task> getTasks() { return tasks; }
    /** @param tasks lista de tareas */
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }
}

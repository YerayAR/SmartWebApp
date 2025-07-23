package com.example.smartwebapp.repositories;
/*
 * Repositorio JPA para gestionar tareas.
 */

import com.example.smartwebapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link Task}.
 * Extiende de {@link JpaRepository} para heredar operaciones CRUD estándar.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Métodos CRUD heredados de JpaRepository
}

package com.example.smartwebapp.services;
/*
 * Servicio de dominio responsable de la logica de tareas.
 */

import com.example.smartwebapp.models.Task;
import com.example.smartwebapp.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de dominio para operaciones con tareas.
 */
@Service
public class TaskService {

    /** Repositorio de tareas. */
    private final TaskRepository taskRepository;

    /**
     * Constructor que recibe el repositorio a utilizar.
     *
     * @param taskRepository repositorio inyectado
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Obtiene todas las tareas existentes.
     *
     * @return lista de tareas
     */
    public List<Task> findAll() {
        // Recupera todas las tareas almacenadas
        return taskRepository.findAll();
    }

    /**
     * Guarda una tarea.
     *
     * @param task tarea a persistir
     * @return tarea guardada
     */
    public Task save(Task task) {
        // Persiste la entidad y la devuelve con su id asignado
        return taskRepository.save(task);
    }

    /**
     * Busca una tarea por su identificador.
     *
     * @param id identificador
     * @return tarea encontrada
     * @throws RuntimeException si no existe
     */
    public Task findById(Long id) {
        // Si no se encuentra la tarea se lanza una excepción informativa
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    /**
     * Busca una tarea por su identificador.
     *
     * @param id identificador
     * @return Optional con la tarea si existe
     */
    public Optional<Task> findByIdOptional(Long id) {
        return taskRepository.findById(id);
    }

    /**
     * Elimina una tarea por su identificador.
     *
     * @param id identificador de la tarea a eliminar
     */
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}

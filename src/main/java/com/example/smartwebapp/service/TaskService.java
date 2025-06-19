package com.example.smartwebapp.service;

import com.example.smartwebapp.model.Task;
import com.example.smartwebapp.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

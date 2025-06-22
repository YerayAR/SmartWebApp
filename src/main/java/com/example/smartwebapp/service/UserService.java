package com.example.smartwebapp.service;

import com.example.smartwebapp.model.User;
import com.example.smartwebapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de dominio para operaciones con usuarios.
 */
@Service
public class UserService {

    /** Repositorio de usuarios. */
    private final UserRepository userRepository;

    /**
     * Constructor de la clase.
     *
     * @param userRepository repositorio inyectado
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return lista de usuarios
     */
    public List<User> findAll() {
        // Obtiene todas las entidades desde la base de datos
        return userRepository.findAll();
    }

    /**
     * Guarda un nuevo usuario.
     *
     * @param user entidad a persistir
     * @return usuario guardado
     */
    public User save(User user) {
        // Delegamos la persistencia al repositorio JPA
        return userRepository.save(user);
    }

    /**
     * Busca un usuario por su identificador.
     *
     * @param id identificador del usuario
     * @return Optional con usuario encontrado
     */
    public java.util.Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}

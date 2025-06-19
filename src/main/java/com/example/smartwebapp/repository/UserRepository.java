package com.example.smartwebapp.repository;

import com.example.smartwebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link User}.
 * Proporciona acceso a operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Se utilizan los métodos CRUD heredados de JpaRepository
}

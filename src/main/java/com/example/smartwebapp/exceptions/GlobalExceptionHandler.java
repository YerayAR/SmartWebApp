package com.example.smartwebapp.exceptions;
/*
 * Gestion centralizado de excepciones para la API REST.
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones para la capa REST.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja los casos en los que no se encuentra un recurso.
     *
     * @param ex excepción recibida
     * @return respuesta con código 404 y el mensaje correspondiente
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Manejo genérico para cualquier otra excepción.
     *
     * @param ex excepción producida
     * @return respuesta con código 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        // Se evita exponer detalles internos devolviendo un mensaje genérico
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal error");
    }
}

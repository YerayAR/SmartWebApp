package com.example.smartwebapp.exception;

/**
 * Excepción lanzada cuando no se encuentra un recurso solicitado.
 * Extiende de {@link RuntimeException} para facilitar su propagación
 * en la capa de controladores.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Crea una nueva excepción con el mensaje indicado.
     *
     * @param message mensaje descriptivo
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

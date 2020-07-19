package dev.efrain.pokeapi.service.error;

import lombok.Getter;

/**
 * General Exception for business related exceptions.
 */
public class BusinessLogicException extends RuntimeException {

    /**
     * Message for the exception.
     */
    @Getter
    private String message;

    public BusinessLogicException(String message) {
        this.message = message;
    }

    public BusinessLogicException() {}
}

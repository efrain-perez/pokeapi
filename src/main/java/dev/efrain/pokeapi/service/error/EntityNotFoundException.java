package dev.efrain.pokeapi.service.error;

/**
 * Exception used when any of the entities is not found.
 */
public class EntityNotFoundException extends BusinessLogicException {

    public EntityNotFoundException() {}

    public EntityNotFoundException(String message) {
        super(message);
    }

}

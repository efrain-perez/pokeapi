package dev.efrain.pokeapi.service.error;

/**
 * Exception used when a move could not be retrieved.
 */
public class MoveNotFoundException extends BusinessLogicException {

    public MoveNotFoundException(String idOrName) {
        super("Move with id or name '" + idOrName + "' not found");
    }
}

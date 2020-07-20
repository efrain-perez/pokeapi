package dev.efrain.pokeapi.service.error;

/**
 * Exception used when a type could not be retrieved.
 */
public class InvalidPageSizeException extends BusinessLogicException {

    public InvalidPageSizeException(Integer pageSize) {
        super("Page size " + pageSize + " is not valid.");
    }
}

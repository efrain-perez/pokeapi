package dev.efrain.pokeapi.service.error;

/**
 * Exception used when a type could not be retrieved.
 */
public class InvalidOffsetException extends BusinessLogicException {

    public InvalidOffsetException(Integer offset) {
        super("Offset value " + offset + " is not valid. Please use positive values only.");
    }
}

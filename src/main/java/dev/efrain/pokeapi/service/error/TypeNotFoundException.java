package dev.efrain.pokeapi.service.error;

/**
 * Exception used when a type could not be retrieved.
 */
public class TypeNotFoundException extends EntityNotFoundException {

    public TypeNotFoundException(String idOrName) {
        super("Type with id or name '" + idOrName + "' not found");
    }
}

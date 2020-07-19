package dev.efrain.pokeapi.service.error;

/**
 * Exception used when a pokemon could not be retrieved.
 */
public class PokemonNotFoundException extends EntityNotFoundException {

    public PokemonNotFoundException(String idOrName) {
        super("Pokemon with id or name '" + idOrName + "' not found");
    }
}

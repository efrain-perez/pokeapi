package dev.efrain.pokeapi.data;

import dev.efrain.pokeapi.model.Pokemon;

import java.util.Optional;

/**
 * Interface for getting all the information related to Pokemons.
 */
public interface PokemonDao {

    /**
     * Retrieve a Pokemon by its id.
     * @param id Identifier for the pokemon
     * @return Pokemon retrieved.
     */
    Optional<Pokemon> getPokemonById(Integer id);

    /**
     * Retrieve a Pokemon by its name.
     * @param name Name of the pokemon
     * @return Pokemon retrieved.
     */
    Optional<Pokemon> getPokemonByName(String name);
}

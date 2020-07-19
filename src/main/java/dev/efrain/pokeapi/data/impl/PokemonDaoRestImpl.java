package dev.efrain.pokeapi.data.impl;

import dev.efrain.pokeapi.data.PokemonDao;
import dev.efrain.pokeapi.model.Pokemon;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementation for getting all the information related to Pokemons.
 *
 * This implementation uses PokeAPI.
 * For more information of the API visit https://pokeapi.co
 */
@Repository
public class PokemonDaoRestImpl extends AbstractPokeapiRestImpl implements PokemonDao {

    /**
     * Base URL for getting pokemon related information.
     */
    private static final String POKEMON_BASE_URL = "/pokemon/";

    /**
     * Retrieve a Pokemon by its id.
     * @param id Identifier for the pokemon
     * @return Pokemon retrieved.
     */
    @Override
    @Cacheable("pokemonById")
    public Optional<Pokemon> getPokemonById(Integer id) {
        return getEntityByIdOrName(POKEMON_BASE_URL + id, Pokemon.class);
    }

    /**
     * Retrieve a Pokemon by its name.
     * @param name Name of the pokemon
     * @return Pokemon retrieved.
     */
    @Override
    @Cacheable("pokemonByName")
    public Optional<Pokemon> getPokemonByName(String name) {
        return getEntityByIdOrName(POKEMON_BASE_URL + name, Pokemon.class);
    }

}

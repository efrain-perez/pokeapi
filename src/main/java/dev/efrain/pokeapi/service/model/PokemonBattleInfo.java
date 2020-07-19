package dev.efrain.pokeapi.service.model;
import lombok.Data;

import java.io.Serializable;

/**
 * Used to provide useful information for a battle between two pokemons.
 */
@Data
public class PokemonBattleInfo implements Serializable {

    private static final long serialVersionUID = -8634110736768303141L;

    /**
     * Information for the first pokemon
     */
    private PokemonInfo firstPokemon;

    /**
     * Information for the second pokemon
     */
    private PokemonInfo secondPokemon;

    public PokemonBattleInfo() {
        firstPokemon = new PokemonInfo();
        secondPokemon = new PokemonInfo();
    }
}

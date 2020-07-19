package dev.efrain.pokeapi.service;

import dev.efrain.pokeapi.service.model.PokemonBattleInfo;

/**
 * Interface pokemon related operations.
 */
public interface BattleService {

    /**
     * Summarizes relevant information about a battle between 2 pokemons.
     *
     * @param firstPokemon ID or name for the first pokemon.
     * @param secondPokemon Id or name for the second pokemon.
     * @return Information retrieved for the battle.
     */
    PokemonBattleInfo getBattleInfo(String firstPokemon, String secondPokemon);

}

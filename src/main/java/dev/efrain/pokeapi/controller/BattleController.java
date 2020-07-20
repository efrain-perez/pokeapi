package dev.efrain.pokeapi.controller;

import dev.efrain.pokeapi.service.BattleService;
import dev.efrain.pokeapi.service.model.PokemonBattleInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class BattleController {

    @Autowired
    private BattleService battleService;

    /**
     * Gets information about a battle between 2 pokemons.
     * This information includes name, types and attackMultiplier.
     * <p>
     * The order of first and second pokemon only affects the order in the results.
     *
     * @param firstPokemon  ID or name of the first pokemon.
     * @param secondPokemon ID or name of the second pokemon.
     * @return Battle information for the pokemons provided.
     */
    @ApiOperation(value = "Get relevant information for a battle between two pokemons.",
            notes = "Provides information about a battle given 2 pokemons. This information includes name, types and " +
                    "how one pokemon affects the other.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Petition processed successfully"),
            @ApiResponse(code = 400, message = "One pokemon could not be identified.")
    })
    @GetMapping(value = "/battle")
    @ResponseBody
    public PokemonBattleInfo getBattleInformation(
            @ApiParam(value = "ID or name for the first pokemon", example = "charmander")
            @RequestParam String firstPokemon,
            @ApiParam(value = "ID or name for the second pokemon", example = "bulbasaur")
            @RequestParam String secondPokemon) {
        return battleService.getBattleInfo(firstPokemon, secondPokemon);
    }
}

package dev.efrain.pokeapi.controller;

import dev.efrain.pokeapi.service.BattleService;
import dev.efrain.pokeapi.service.MoveService;
import dev.efrain.pokeapi.service.model.MoveTranslation;
import dev.efrain.pokeapi.service.model.PokemonBattleInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Pokemon related information.
 */
@Api(value = "This is a test")
@RestController
public class PokemonController {

    /**
     * Default language for moves when not provided.
     */
    private static final String DEFAULT_LANGUAGE = "en";

    @Autowired
    private BattleService battleService;

    @Autowired
    private MoveService moveService;

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
    public PokemonBattleInfo getBattleMultiplier(
            @ApiParam(value = "ID or name for the first pokemon", example = "charmander")
            @RequestParam String firstPokemon,
            @ApiParam(value = "ID or name for the second pokemon", example = "bulbasaur")
            @RequestParam String secondPokemon) {
        return battleService.getBattleInfo(firstPokemon, secondPokemon);
    }

    /**
     * Gets the list of shared moves for the pokemons provided with the desired language translation.
     * This list is ordered by the move ID.
     *
     * @param pokemons List of pokemons IDs or Names (each pokemon can be either ID or Name)
     * @param language ID or name of the desired language. Defaults to 'en'.
     * @param size     Maximum size of the list. Defaults to 10.
     * @param offset   Number of moves to skip from the beginning. Defaults to 0;
     * @return List of shared moves.
     */
    @ApiOperation(value = "Get shared moves between multiple pokemons.",
            notes = "Get a list of the shared moves among the pokemons provided in the desired language. The list can " +
                    "be limited and can start at any provided point.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Petition processed successfully"),
            @ApiResponse(code = 400, message = "One pokemon could not be identified or " +
                    "the language provided is not valid.")
    })
    @GetMapping(value = "/shared-moves")
    @ResponseBody
    public List<MoveTranslation> getBattleMultiplier(
            @ApiParam(value = "ID or Name for the pokemons", allowMultiple = true) @RequestParam List<String> pokemons,
            @ApiParam(value = "Preferred language", allowableValues = "ja-Hrkt,roomaji,ko,zh-Hant,fr,de,es,it,en," +
                    "cs,ja,zh-Hans,pt-BR")
            @RequestParam(required = false, defaultValue = DEFAULT_LANGUAGE) String language,
            @ApiParam(value = "Size of the list") @RequestParam(required = false, defaultValue = "10") Integer size,
            @ApiParam(value = "Offset for the list") @RequestParam(required = false, defaultValue = "0") Integer offset) {
        return moveService.getMatchingMoves(pokemons, language, size, offset);
    }


}

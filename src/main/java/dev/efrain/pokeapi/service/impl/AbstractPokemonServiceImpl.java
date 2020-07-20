package dev.efrain.pokeapi.service.impl;

import dev.efrain.pokeapi.data.LanguageDao;
import dev.efrain.pokeapi.data.MoveDao;
import dev.efrain.pokeapi.data.PokemonDao;
import dev.efrain.pokeapi.data.TypeDao;
import dev.efrain.pokeapi.model.*;
import dev.efrain.pokeapi.service.error.MoveNotFoundException;
import dev.efrain.pokeapi.service.error.PokemonNotFoundException;
import dev.efrain.pokeapi.service.error.TypeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

/**
 * Base class for service implementations. It contains methods to get base entities by name and/or id.
 */
public class AbstractPokemonServiceImpl {

    /**
     * Dao for pokemon information.
     */
    @Autowired
    protected PokemonDao pokemonDao;

    /**
     * Dao for type information.
     */
    @Autowired
    protected TypeDao typeDao;

    /**
     * Dao for move information.
     */
    @Autowired
    protected MoveDao moveDao;

    /**
     * Dao for language information.
     */
    @Autowired
    protected LanguageDao languageDao;

    /**
     * Gets a pokemon by ID or name.
     *
     * @param idOrName String containing either the ID or Name of the pokemon.
     * @return retrieved pokemon.
     */
    protected Pokemon getPokemonByIdOrName(String idOrName) {
        Optional<Pokemon> pokemon;
        if (idOrName.matches("[0-9]+")) {
            pokemon = pokemonDao.getPokemonById(Integer.valueOf(idOrName));
        } else {
            pokemon = pokemonDao.getPokemonByName(idOrName.toLowerCase());
        }
        if (!pokemon.isPresent()) {
            throw new PokemonNotFoundException(idOrName);
        }
        return pokemon.get();
    }

    /**
     * Gets a Type by  name.
     *
     * @param typeName String containing the Name of the type.
     * @return retrieved type.
     */
    protected Type getPokemonTypeByName(String typeName) {
        Optional<Type> type = typeDao.getTypeByName(typeName);
        if (!type.isPresent()) {
            throw new TypeNotFoundException(typeName);
        }
        return type.get();
    }

    /**
     * Gets a Move by  name.
     *
     * @param moveName String containing the Name of the move.
     * @return retrieved move.
     */
    protected Move getPokemonMoveByName(String moveName) {
        Optional<Move> move = moveDao.getMoveByName(moveName);
        if (!move.isPresent()) {
            throw new MoveNotFoundException(moveName);
        }
        return move.get();
    }

    /**
     * Gets a language by ID or name.
     *
     * @param languageIdOrName String containing either the ID or Name of the language.
     * @return retrieved language.
     */
    protected Language getLanguageByIdOrName(String languageIdOrName) {
        Optional<Language> language;
        if (languageIdOrName.matches("[0-9]+")) {
            language = languageDao.getLanguageById(Integer.valueOf(languageIdOrName));
        } else {
            language = languageDao.getLanguageByName(languageIdOrName.toLowerCase());
        }
        if (!language.isPresent()) {
            throw new PokemonNotFoundException(languageIdOrName);
        }
        return language.get();
    }
}

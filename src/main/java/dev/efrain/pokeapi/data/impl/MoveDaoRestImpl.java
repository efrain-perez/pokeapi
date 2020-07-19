package dev.efrain.pokeapi.data.impl;

import dev.efrain.pokeapi.data.MoveDao;
import dev.efrain.pokeapi.model.Move;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementation for getting all the information related to moves.
 *
 * This implementation uses PokeAPI.
 * For more information of the API visit https://pokeapi.co
 */
@Repository
public class MoveDaoRestImpl extends AbstractPokeapiRestImpl implements MoveDao {

    /**
     * Base URL for getting move related information.
     */
    private static final String MOVE_BASE_URL = "/move/";

    /**
     * Retrieve a Move by its ID.
     *
     * @param id ID of the move.
     * @return Move retrieved.
     */
    @Override
    @Cacheable("moveById")
    public Optional<Move> getMoveById(Integer id) {
        return getEntityByIdOrName(MOVE_BASE_URL + id, Move.class);
    }

    /**
     * Retrieve a Move by its name.
     *
     * @param name Name of the move.
     * @return Move retrieved.
     */
    @Override
    @Cacheable("moveByName")
    public Optional<Move> getMoveByName(String name) {
        return getEntityByIdOrName(MOVE_BASE_URL + name, Move.class);
    }
}

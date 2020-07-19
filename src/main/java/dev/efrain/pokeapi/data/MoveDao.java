package dev.efrain.pokeapi.data;

import dev.efrain.pokeapi.model.Move;

import java.util.Optional;

/**
 * Interface to get information about pokemon moves.
 */
public interface MoveDao {

    /**
     * Retrieve a Move by its ID.
     * @param id ID of the move.
     * @return Move retrieved.
     */
    Optional<Move> getMoveById(Integer id);

    /**
     * Retrieve a Move by its name.
     * @param name Name of the move.
     * @return Move retrieved.
     */
    Optional<Move> getMoveByName(String name);

}

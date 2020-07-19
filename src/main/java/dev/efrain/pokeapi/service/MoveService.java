package dev.efrain.pokeapi.service;

import dev.efrain.pokeapi.service.model.MoveTranslation;
import java.util.List;

/**
 * Interface for Move related operations.
 */
public interface MoveService {

    /**
     * Gets the list of shared moves for the pokemons provided with the desired language translation.
     * This list is ordered by the move ID.
     *
     * @param pokemons List of pokemons IDs or Names (each pokemon can be either ID or Name)
     * @param language ID or name of the desired language.
     * @param pageSize Maximum size of the list.
     * @param offset Number of moves to skip from the beginning.
     * @return List of shared moves.
     */
    List<MoveTranslation> getMatchingMoves(List<String> pokemons, String language, Integer pageSize, Integer offset);
}

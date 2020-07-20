package dev.efrain.pokeapi.service.impl;

import dev.efrain.pokeapi.model.*;
import dev.efrain.pokeapi.service.MoveService;
import dev.efrain.pokeapi.service.error.BusinessLogicException;
import dev.efrain.pokeapi.service.model.MoveTranslation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * MoveService implementation, used to get Move related information.
 */
@Service
public class MoveServiceDefaultImpl extends AbstractPokemonServiceImpl implements MoveService {

    /**
     * Gets the list of shared moves for the pokemons provided with the desired language translation.
     * This list is ordered by the move ID.
     *
     * @param pokemonIdsOrNames List of pokemons IDs or Names (each pokemon can be either ID or Name)
     * @param languageIdOrName ID or name of the desired language.
     * @param pageSize Maximum size of the list.
     * @param offset   Number of moves to skip from the beginning.
     * @return List of shared moves.
     */
    @Override
    public List<MoveTranslation> getMatchingMoves(List<String> pokemonIdsOrNames, String languageIdOrName, Integer pageSize, Integer offset) {

        Language language = getLanguageByIdOrName(languageIdOrName);
        List<Pokemon> pokemons = pokemonIdsOrNames.parallelStream().map(p -> getPokemonByIdOrName(p)).collect(Collectors.toList());
        if (pokemons.isEmpty()) {
            return new ArrayList<>();
        }

        Set<String> matchingMoves = pokemons.get(0).getMoves().stream()
                .map(MoveSimpleWrapper::getName)
                .collect(Collectors.toCollection(HashSet::new));
        for (int i = 1; i < pokemons.size(); i++) {
            Set<String> currentMatchingMoves = new HashSet<>();
            for (MoveSimpleWrapper move : pokemons.get(i).getMoves()) {
                if (matchingMoves.contains(move.getName())) {
                    currentMatchingMoves.add(move.getName());
                }
            }
            matchingMoves = currentMatchingMoves;
        }

        List<Move> detailedMatchingMoves =  matchingMoves.parallelStream()
                .map(m -> getPokemonMoveByName(m))
                .collect(Collectors.toCollection(ArrayList::new));

        Collections.sort(detailedMatchingMoves);

        List<MoveTranslation> moves = new ArrayList<>();
        for(int i = offset; i < detailedMatchingMoves.size() && moves.size() < pageSize; i++){
            Move currentMove = detailedMatchingMoves.get(i);
            MoveTranslation moveTranslation = new MoveTranslation();
            moveTranslation.setId(currentMove.getId());
            moveTranslation.setName(getMoveTranslation(currentMove, language));
            moveTranslation.setType(getTypeTranslation(currentMove.getType(), language));
            moves.add(moveTranslation);
        }
        return moves;
    }

    /**
     * Gets the translation of the move in the provided language.
     *
     * @param move Move to get translated.
     * @param language Desired language.
     * @return Name of the moved in the provided language.
     */
    private String getMoveTranslation(Move move, Language language) {
        return move.getNames().stream()
                .filter(m -> m.getLanguage().equals(language.getName()))
                .map(m -> m.getName())
                .findFirst()
                .orElseThrow(() -> new BusinessLogicException(
                        "Couldn't find language " + language.getName() + " for move " + move.getName()));
    }

    /**
     * Retrieves a Type and gets the translation in the provided language.
     *
     * @param typeName Type to be translated.
     * @param language Desired language.
     * @return Name of the type in the provided language.
     */
    private String getTypeTranslation(String typeName, Language language) {
        Type type = getPokemonTypeByName(typeName);
        return type.getNames().stream()
                .filter(t -> t.getLanguage().equals(language.getName()))
                .map(t -> t.getName())
                .findFirst()
                .orElseThrow(() -> new BusinessLogicException(
                        "Couldn't find language " + language.getName() + " for type " + typeName));
    }
}

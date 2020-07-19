package dev.efrain.pokeapi.service.impl;

import dev.efrain.pokeapi.model.Pokemon;
import dev.efrain.pokeapi.model.Type;
import dev.efrain.pokeapi.model.TypeSimpleWrapper;
import dev.efrain.pokeapi.service.model.PokemonBattleInfo;
import dev.efrain.pokeapi.service.BattleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service implementation for battle related information.
 */
@Service
public class BattleServiceDefaultImpl extends AbstractPokemonServiceImpl implements BattleService {

    /**
     * Summarizes relevant information about a battle between 2 pokemons.
     *
     * @param firstPokemonIdOrName ID or name for the first pokemon.
     * @param secondPokemonIdOrName Id or name for the second pokemon.
     * @return Information retrieved for the battle.
     */
    @Override
    public PokemonBattleInfo getBattleInfo(String firstPokemonIdOrName, String secondPokemonIdOrName) {
        Pokemon firstPokemon = getPokemonByIdOrName(firstPokemonIdOrName);
        Pokemon secondPokemon = getPokemonByIdOrName(secondPokemonIdOrName);
        List<Type> firstPokemonTypes = getPokemonTypeInformation(firstPokemon.getTypes());

        Double damageGiven = 1.0;
        Double damageTaken = 1.0;
        for (Type type : firstPokemonTypes) {
            Map<String, Double> damageGivenMultiplier = getDamageGivenMultiplier(type);
            Map<String, Double> damageTakenMultiplier = getDamageTakenMultiplier(type);

            for (TypeSimpleWrapper typeWrapper : secondPokemon.getTypes()) {
                if (damageGivenMultiplier.containsKey(typeWrapper.getName())) {
                    damageGiven *= damageGivenMultiplier.get(typeWrapper.getName());
                }
                if (damageTakenMultiplier.containsKey(typeWrapper.getName())) {
                    damageTaken *= damageTakenMultiplier.get(typeWrapper.getName());
                }
            }
        }

        PokemonBattleInfo battleInfo = new PokemonBattleInfo();
        battleInfo.getFirstPokemon().setName(firstPokemon.getName());
        battleInfo.getFirstPokemon().setTypes(getTypeNamesFromWrapper(firstPokemon.getTypes()));
        battleInfo.getFirstPokemon().setAttackMultiplier(damageGiven);

        battleInfo.getSecondPokemon().setName(secondPokemon.getName());
        battleInfo.getSecondPokemon().setTypes(getTypeNamesFromWrapper(secondPokemon.getTypes()));
        battleInfo.getSecondPokemon().setAttackMultiplier(damageTaken);

        return battleInfo;
    }

    /**
     * Get a list of detailed information for the provided list of type names.
     * @param types List of type names.
     * @return Retrieved detailed information.
     */
    private List<Type> getPokemonTypeInformation(List<TypeSimpleWrapper> types) {
        return types.stream()
                .map(t -> getPokemonTypeByName(t.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Get the list of name out of the provided List of type wrappers.
     * @param types Wrapped types.
     * @return
     */
    private List<String> getTypeNamesFromWrapper(List<TypeSimpleWrapper> types) {
        return types.stream().map(TypeSimpleWrapper::getName).collect(Collectors.toList());
    }

    /**
     * Builds a map containing the damage multipliers for the given type.
     *
     * For example, if the type is Fire and the map contains <water, 2.0>
     * this means that Fire takes 2x the normal damage.
     *
     * @param type Type containing the damage multiplier information.
     * @return Map with the damage multiplier information.
     */
    private Map<String, Double> getDamageTakenMultiplier(Type type) {
        Map<String, Double> damageTakenMultiplier = new HashMap<>();
        for (TypeSimpleWrapper typeWrapper : type.getDamageRelations().getDoubleDamageFrom()) {
            damageTakenMultiplier.put(typeWrapper.getName(), 2.0);
        }
        for (TypeSimpleWrapper typeWrapper : type.getDamageRelations().getHalfDamageFrom()) {
            damageTakenMultiplier.put(typeWrapper.getName(), 0.5);
        }
        for (TypeSimpleWrapper typeWrapper : type.getDamageRelations().getNoDamageFrom()) {
            damageTakenMultiplier.put(typeWrapper.getName(), 0.0);
        }

        return damageTakenMultiplier;
    }

    /**
     * Builds a map containing the damage multipliers for the given type.
     *
     * For example, if the type is Fire and the map contains <grass, 2.0>
     * this means that a pokemon with Fire type will inflict 2x damage to a grass type pokemon.
     *
     * @param type Type containing the damage multiplier information.
     * @return Map with the damage multiplier information.
     */
    private Map<String, Double> getDamageGivenMultiplier(Type type) {
        Map<String, Double> damageGivenMultiplier = new HashMap<>();
        for (TypeSimpleWrapper typeWrapper : type.getDamageRelations().getDoubleDamageTo()) {
            damageGivenMultiplier.put(typeWrapper.getName(), 2.0);
        }
        for (TypeSimpleWrapper typeWrapper : type.getDamageRelations().getHalfDamageTo()) {
            damageGivenMultiplier.put(typeWrapper.getName(), 0.5);
        }
        for (TypeSimpleWrapper typeWrapper : type.getDamageRelations().getNoDamageTo()) {
            damageGivenMultiplier.put(typeWrapper.getName(), 0.0);
        }

        return damageGivenMultiplier;
    }

}

package dev.efrain.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * List of damage multipliers for a certain type.
 *
 * Each *DamageFrom list means that it will take more or less damage if the attacker is on the list.
 * For example if the 'fire' type is in the doubleDamageFrom list, it means that the current type is going to take
 * 2x damage when fighting fire type pokemons.
 *
 * Inversely, each *DamageTo list means that it will inflict more or less damage if the defender is on the list.
 * For example if the 'fire' type is in the doubleDamageTo list, it means that the current type is going to inflict
 * 2x damage when fighting fire type pokemons.
 */
@Data
public class TypeDamageRelation implements Serializable {

    private static final long serialVersionUID = -362809862130422148L;

    /**
     * List of types that are going to inflict 2x damage when the current type is defending.
     */
    @JsonProperty("double_damage_from")
    private List<TypeSimpleWrapper> doubleDamageFrom;

    /**
     * List of types that are going to receive 2x damage when the current type is attacking.
     */
    @JsonProperty("double_damage_to")
    private List<TypeSimpleWrapper> doubleDamageTo;

    /**
     * List of types that are going to inflict 0.5x damage when the current type is defending.
     */
    @JsonProperty("half_damage_from")
    private List<TypeSimpleWrapper> halfDamageFrom;

    /**
     * List of types that are going to receive 0.5x damage when the current type is attacking.
     */
    @JsonProperty("half_damage_to")
    private List<TypeSimpleWrapper> halfDamageTo;

    /**
     * List of types that are going to inflict 0 damage when the current type is defending.
     */
    @JsonProperty("no_damage_from")
    private List<TypeSimpleWrapper> noDamageFrom;

    /**
     * List of types that are going to receive 0 damage when the current type is attacking.
     */
    @JsonProperty("no_damage_to")
    private List<TypeSimpleWrapper> noDamageTo;


}

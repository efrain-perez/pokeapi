package dev.efrain.pokeapi.service.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to provide information about a pokemon and its attack power against other.
 */
@Data
public class PokemonInfo implements Serializable {

    private static final long serialVersionUID = -8055713964052615776L;

    /**
     * Name of the pokemon.
     */
    private String name;
    /**
     * Attack multiplier. Damage it will inflict to the other pokemon.
     */
    private Double attackMultiplier;
    /**
     * List of type names.
     */
    private List<String> types;

    public PokemonInfo() {
        types = new ArrayList<>();
    }

}

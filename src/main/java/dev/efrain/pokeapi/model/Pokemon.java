package dev.efrain.pokeapi.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Pokemon entity, used to retrieve Pokemon detailed information.
 */
@Data
public class Pokemon implements Serializable {

    private static final long serialVersionUID = 4095540157440434434L;

    /**
     * ID of the pokemon
     */
    private Integer id;
    /**
     * Pokemon name in the default language (English).
     * The PokeAPI implementation does not provide alternate names.
     */
    private String name;
    /**
     * List of move names.
     */
    private List<MoveSimpleWrapper> moves;
    /**
     * List of type names.
     */
    private List<TypeSimpleWrapper> types;


}

package dev.efrain.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Type of pokemon or move. This contains detailed information for a Type.
 * For simple type information use TypeSimpleWrapper.
 */
@Data
public class Type implements Serializable {

    private static final long serialVersionUID = -428847627670570607L;

    /**
     * Damage taken and given for the type.
     */
    @JsonProperty("damage_relations")
    private TypeDamageRelation damageRelations;
    /**
     * ID of the type.
     */
    private Integer id;
    /**
     * Default name for the type. The default name is in English.
     */
    private String name;
    /**
     * List of translations for the type.
     */
    private List<AlternateName> names;

}

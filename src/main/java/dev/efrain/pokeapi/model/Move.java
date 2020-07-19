package dev.efrain.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Move of a Pokemon. Used to get detailed information for a pokemon move.
 * For simple uses like the name of the move use MoveSimpleWrapper.
 */
@Data
public class Move implements Serializable, Comparable<Move> {

    private static final long serialVersionUID = 641438761071592247L;
    
    /**
     * ID of the move.
     */
    private Integer id;
    /**
     * Default name for the move. The default name is in English.
     */
    private String name;
    /**
     * List of translations for the move.
     */
    private List<AlternateName> names;
    /**
     * Move Type.
     */
    private String type;

    /**
     * Get the type.name property.
     * This was done this way to avoid having a whole class just to get the name of the type.
     * @param type Map of properties for the 'type' JSON property.
     */
    @JsonProperty("type")
    private void unpackTypeFromNestedObject(Map<String, String> type) {
        this.type = type.get("name");
    }

    /**
     * Compare two moves. This is used for sorting.
     * @param move Move to compare.
     * @return Result of the comparison.
     */
    @Override
    public int compareTo(Move move) {
        return this.getId().compareTo(move.getId());
    }
}

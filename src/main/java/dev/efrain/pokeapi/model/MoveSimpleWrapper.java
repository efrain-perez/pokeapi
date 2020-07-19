package dev.efrain.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * This is to get the move name in the Pokemon entity.
 * For detailed Move information use Move.
 */
@Data
public class MoveSimpleWrapper implements Serializable {

    private static final long serialVersionUID = 7431517142518409418L;

    /**
     * Name of the move.
     */
    private String name;

    /**
     * Get the move.name property.
     * This was done this way to avoid having a whole class just to get the name of the move.
     * @param move Map of properties for the 'move' JSON property.
     */
    @JsonProperty("move")
    private void unpackNameFromNestedObject(Map<String, String> move) {
        this.name = move.get("name");
    }
}

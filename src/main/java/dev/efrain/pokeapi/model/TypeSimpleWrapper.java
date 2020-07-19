package dev.efrain.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * This is to get the type name in the Pokemon entity.
 * For detailed type information use Type.
 */
@Data
public class TypeSimpleWrapper implements Serializable {

    private static final long serialVersionUID = -4155687903618773751L;

    /**
     * Name of the type.
     */
    private String name;

    /**
     * Get the type.name property.
     * This was done this way to avoid having a whole class just to get the name of the type.
     * @param type Map of properties for the 'type' JSON property.
     */
    @JsonProperty("type")
    private void unpackNameFromNestedObject(Map<String, String> type) {
        this.name = type.get("name");
    }
}

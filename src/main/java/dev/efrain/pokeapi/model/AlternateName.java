package dev.efrain.pokeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Alternate name used for some entities like moves and types.
 * These names are the ones used for other languages.
 */
@Data
public class AlternateName implements Serializable {

    private static final long serialVersionUID = 518382510754140666L;

    /**
     * Alternate name.
     */
    private String name;

    /**
     * Language for the alternate name.
     */
    private String language;

    /**
     * Get the language.name property.
     * This was done this way to avoid having a whole class just to get the name of the language.
     * @param language Map of properties for the 'language' JSON property.
     */
    @JsonProperty("language")
    private void unpackLanguageFromNestedObject(Map<String, String> language) {
        this.language = language.get("name");
    }
}

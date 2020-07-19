package dev.efrain.pokeapi.data;

import dev.efrain.pokeapi.model.Language;

import java.util.Optional;

/**
 * Interface to get information about the supported languages.
 */
public interface LanguageDao {

    /**
     * Retrieve Language by its ID.
     * @param id ID of the language.
     * @return Language retrieved.
     */
    Optional<Language> getLanguageById(Integer id);

    /**
     * Retrieve Language by its name.
     * @param name Name of the language.
     * @return Language retrieved.
     */
    Optional<Language> getLanguageByName(String name);
}

package dev.efrain.pokeapi.data.impl;

import dev.efrain.pokeapi.data.LanguageDao;
import dev.efrain.pokeapi.model.Language;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementation for getting all the information related to languages.
 *
 * This implementation uses PokeAPI.
 * For more information of the API visit https://pokeapi.co
 */
@Repository
public class LanguageDaoRestImpl extends AbstractPokeapiRestImpl implements LanguageDao {

    /**
     * Base URL for getting language related information.
     */
    private static final String LANGUAGE_BASE_URL = "/language/";

    /**
     * Retrieve Language by its ID.
     *
     * @param id ID of the language.
     * @return Language retrieved.
     */
    @Override
    @Cacheable("languageById")
    public Optional<Language> getLanguageById(Integer id) {
        return getEntityByIdOrName(LANGUAGE_BASE_URL + id, Language.class);
    }

    /**
     * Retrieve Language by its name.
     *
     * @param name Name of the language.
     * @return Language retrieved.
     */
    @Override
    @Cacheable("languageByName")
    public Optional<Language> getLanguageByName(String name) {
        return getEntityByIdOrName(LANGUAGE_BASE_URL + name, Language.class);
    }
}

package dev.efrain.pokeapi.data.impl;

import dev.efrain.pokeapi.data.TypeDao;
import dev.efrain.pokeapi.model.Type;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementation for getting all the information related to types.
 *
 * This implementation uses PokeAPI.
 * For more information of the API visit https://pokeapi.co
 */
@Repository
public class TypeDaoRestImpl extends AbstractPokeapiRestImpl implements TypeDao {

    /**
     * Base URL for getting type related information.
     */
    private static final String TYPE_BASE_URL = "/type/";

    /**
     * Retrieve a Type by its ID.
     *
     * @param id ID of the type.
     * @return Type retrieved.
     */
    @Override
    @Cacheable("typeById")
    public Optional<Type> getTypeById(Integer id) {
        return getEntityByIdOrName(TYPE_BASE_URL + id, Type.class);
    }

    /**
     * Retrieve a Type by its name.
     *
     * @param name Name of the type.
     * @return Type retrieved.
     */
    @Override
    @Cacheable("typeByName")
    public Optional<Type> getTypeByName(String name) {
        return getEntityByIdOrName(TYPE_BASE_URL + name, Type.class);
    }
}

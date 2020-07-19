package dev.efrain.pokeapi.data;

import dev.efrain.pokeapi.model.Type;

import java.util.Optional;

/**
 * Interface to get information related to pokemon/move type.
 */
public interface TypeDao {

    /**
     * Retrieve a Type by its ID.
     * @param id ID of the type.
     * @return Type retrieved.
     */
    Optional<Type> getTypeById(Integer id);

    /**
     * Retrieve a Type by its name.
     * @param name Name of the type.
     * @return Type retrieved.
     */
    Optional<Type> getTypeByName(String name);

}

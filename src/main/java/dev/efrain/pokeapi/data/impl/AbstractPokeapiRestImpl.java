package dev.efrain.pokeapi.data.impl;

import dev.efrain.pokeapi.model.Pokemon;
import dev.efrain.pokeapi.service.error.BusinessLogicException;
import dev.efrain.pokeapi.service.error.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Base class for PokeAPI dao implementations.
 * For more information of the API visit https://pokeapi.co
 */
public abstract class AbstractPokeapiRestImpl<T> {

    /**
     * Base URL for the PokeAPI.
     */
    protected static final String POKEAPI_BASE_URL = "https://pokeapi.co/api/v2";

    /**
     * Rest template that we are going to use to communicate with PokeAPI.
     */
    @Autowired
    protected RestTemplate restTemplate;

    /**
     * Get the default HTTP Entity, this contains a User-Agent that is needed for proper communication with the PokeAPI.
     * @return Default HttpEntity
     */
    protected HttpEntity<String> getDefaultHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "default-pokeapi");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }

    /**
     * Gets a entity by its ID or name.
     *
     * As PokeAPI provides a single method for getting most of its entities which can use either
     * name or id of the type, we will use it for both as well.
     *
     * @param relativeUrl Relative route for the entity. Example "/pokemon/charizard"
     * @return Entity found, or empty if non found.
     */
    protected <T> Optional<T> getEntityByIdOrName(String relativeUrl, Class<T> clazz) {
        HttpEntity<String> entity = getDefaultHttpEntity();

        try {
            ResponseEntity<T> response = restTemplate.exchange(POKEAPI_BASE_URL + relativeUrl,
                    HttpMethod.GET, entity, clazz);
            if(response.getStatusCode().is2xxSuccessful()) {
                return Optional.of(response.getBody());
            } else {
                return Optional.empty();
            }
        } catch(EntityNotFoundException e) {
            return Optional.empty();
        }catch(HttpStatusCodeException e) {
            throw new BusinessLogicException("Error while trying to retrieve entity with url: " + relativeUrl);
        }
    }
}

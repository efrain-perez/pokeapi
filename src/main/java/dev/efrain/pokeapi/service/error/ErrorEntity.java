package dev.efrain.pokeapi.service.error;

import lombok.Data;

import java.io.Serializable;

/**
 * Entity used to let the user what went wrong, for example when a Pokemon is not found.
 */
@Data
public class ErrorEntity implements Serializable {

    private static final long serialVersionUID = -7149299999892278981L;

    /**
     * Message for the user.
     */
    private String message;
}

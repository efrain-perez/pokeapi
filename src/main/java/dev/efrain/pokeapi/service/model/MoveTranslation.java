package dev.efrain.pokeapi.service.model;
import lombok.Data;

import java.io.Serializable;

/**
 * Used to provide a translated move, with (translated) type and id.
 */
@Data
public class MoveTranslation implements Serializable {

    private static final long serialVersionUID = -7903456096378602864L;

    /**
     * ID of the move.
     */
    private Integer id;
    /**
     * Name if the move (Not necessarily in the default language).
     */
    private String name;
    /**
     * Name of the type of move (Not necessarily in the default language).
     */
    private String type;
}

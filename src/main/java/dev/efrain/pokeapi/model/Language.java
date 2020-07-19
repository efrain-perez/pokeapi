package dev.efrain.pokeapi.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Language is used to get translations for entities like Move and Type.
 * Supported languages are: ja-Hrkt, roomaji, ko, zh-Hant, fr, de, es, it, en, cs, ja, zh-Hans, pt-BR.
 */
@Data
public class Language implements Serializable {

    private static final long serialVersionUID = -3233029313380929540L;

    /**
     * Name of the language.
     */
    private String name;
}

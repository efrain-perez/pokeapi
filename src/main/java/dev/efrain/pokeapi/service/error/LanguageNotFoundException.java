package dev.efrain.pokeapi.service.error;

public class LanguageNotFoundException extends EntityNotFoundException {

    public LanguageNotFoundException(String language) {
        super("Language '" + language + "' not found");
    }
}

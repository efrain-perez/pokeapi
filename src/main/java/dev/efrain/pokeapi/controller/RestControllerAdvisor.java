package dev.efrain.pokeapi.controller;

import dev.efrain.pokeapi.service.error.ErrorEntity;
import dev.efrain.pokeapi.service.error.PokemonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Advice on what to do when certain exceptions appear.
 */
@RestControllerAdvice
public class RestControllerAdvisor {

    /**
     * When the PokemonNotFoundException is present, we should let the user know that the pokemon is not valid.
     *
     * @param e Pokemon not found exception gotten.
     * @return Response entity with the message on the exception.
     */
    @ExceptionHandler(value = PokemonNotFoundException.class)
    public ResponseEntity<ErrorEntity> handlePokemonNotfoundException(PokemonNotFoundException e){
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setMessage(e.getMessage());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

}

package dev.efrain.pokeapi.controller;

import dev.efrain.pokeapi.service.error.BusinessLogicException;
import dev.efrain.pokeapi.service.error.ErrorEntity;
import dev.efrain.pokeapi.service.error.InvalidOffsetException;
import dev.efrain.pokeapi.service.error.InvalidPageSizeException;
import dev.efrain.pokeapi.service.error.LanguageNotFoundException;
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
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    /**
     * When the LanguageNotFoundException is present, we should let the user know that the language was not found.
     *
     * @param e Language not found exception gotten.
     * @return Response entity with the message on the exception.
     */
    @ExceptionHandler(value = LanguageNotFoundException.class)
    public ResponseEntity<ErrorEntity> handleLanguageNotfoundException(LanguageNotFoundException e){
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setMessage(e.getMessage());
        return new ResponseEntity<>(errorEntity, HttpStatus.NOT_FOUND);
    }

    /**
     * When the InvalidOffsetException is present, we should let the user know that the selected offset is not valid.
     *
     * @param e Invalid offset exception gotten.
     * @return Response entity with the message on the exception.
     */
    @ExceptionHandler(value = InvalidOffsetException.class)
    public ResponseEntity<ErrorEntity> handleInvalidOffsetException(InvalidOffsetException e){
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setMessage(e.getMessage());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

    /**
     * When the InvalidPageSizeException is present, we should let the user know that
     * the selected page size is not valid.
     *
     * @param e Invalid page size exception gotten.
     * @return Response entity with the message on the exception.
     */
    @ExceptionHandler(value = InvalidPageSizeException.class)
    public ResponseEntity<ErrorEntity> handleInvalidPageSizeException(InvalidPageSizeException e){
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setMessage(e.getMessage());
        return new ResponseEntity<>(errorEntity, HttpStatus.BAD_REQUEST);
    }

    /**
     * When non of the above exceptions is present, but is one of our exceptions, it means that we had something
     * wrong but we can provide some information for further investigation.
     *
     * @param e Business logic exception gotten.
     * @return Response entity with the message on the exception.
     */
    @ExceptionHandler(value = BusinessLogicException.class)
    public ResponseEntity<ErrorEntity> handleBusinessLogicException(BusinessLogicException e){
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setMessage("Something went wrong on our side: " + e.getMessage());
        return new ResponseEntity<>(errorEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * When non of the above exceptions is present, and is NOT one of our exceptions, it means that we had something
     * wrong and we didn't catch it before. This kind of exception should never be happening on normal circumstances.
     *
     * @param e Business logic exception gotten.
     * @return Response entity with the message on the exception.
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorEntity> handleAnyOtherException(Exception e){
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setMessage("Something went really wrong on our side.");
        return new ResponseEntity<>(errorEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

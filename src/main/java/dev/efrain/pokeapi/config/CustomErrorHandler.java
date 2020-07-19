package dev.efrain.pokeapi.config;

import dev.efrain.pokeapi.service.error.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * Custom Error Handler for our application.
 * This class is used for error handling on a rest template.
 */
public class CustomErrorHandler extends DefaultResponseErrorHandler {

    /**
     * Handle errors when given.
     * For our custom implementation we want everything handled as the parent class except for the 404 error.
     * @param response Client http response.
     * @throws IOException When it is not able to read a status code.
     */
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new EntityNotFoundException();
        } else {
            super.handleError(response);
        }
    }
}

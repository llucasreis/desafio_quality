package com.bootcamp.desafio.seuimovelapi.shared.handlers;

import com.bootcamp.desafio.seuimovelapi.shared.errors.ErrorResponse;
import com.bootcamp.desafio.seuimovelapi.shared.errors.ValidationError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidFormatHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ErrorResponse<ValidationError> handleInvalidFormatException(InvalidFormatException exception) {
        String field = exception.getPath().get(0).getFieldName();
        String message = "Valor " + exception.getValue() + " não é válido para o campo";

        ValidationError error = new ValidationError(field, message);

        return new ErrorResponse<>(error);
    }
}

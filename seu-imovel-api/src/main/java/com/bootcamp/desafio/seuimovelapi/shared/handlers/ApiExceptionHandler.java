package com.bootcamp.desafio.seuimovelapi.shared.handlers;

import com.bootcamp.desafio.seuimovelapi.shared.errors.ErrorResponse;
import com.bootcamp.desafio.seuimovelapi.shared.exceptions.ConflictException;
import com.bootcamp.desafio.seuimovelapi.shared.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponse<String> handleNotFoundException(NotFoundException exception) {
        return new ErrorResponse<>(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public ErrorResponse<String> handleConflictException(ConflictException exception) {
        return new ErrorResponse<>(exception.getMessage());
    }
}

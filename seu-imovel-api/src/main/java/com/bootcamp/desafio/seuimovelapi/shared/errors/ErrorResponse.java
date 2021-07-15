package com.bootcamp.desafio.seuimovelapi.shared.errors;

public class ErrorResponse<T> {
    private T error;

    public ErrorResponse() {
    }

    public ErrorResponse(T error) {
        this.error = error;
    }

    public T getError() {
        return error;
    }

    public void setError(T error) {
        this.error = error;
    }
}

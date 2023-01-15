package br.com.attornatus.peopleManagement.exception;

public class InvalidNumberOfCharactersException extends RuntimeException {
    public InvalidNumberOfCharactersException(String message) {
        super(message);
    }
}

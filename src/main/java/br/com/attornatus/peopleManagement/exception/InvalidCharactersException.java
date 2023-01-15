package br.com.attornatus.peopleManagement.exception;

public class InvalidCharactersException extends RuntimeException {

    public InvalidCharactersException(String s){
        super(s);
    }
}

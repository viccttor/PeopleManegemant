package br.com.attornatus.peopleManagement.exception;

public class NullPersonFieldException extends RuntimeException{
    public NullPersonFieldException(String message){
        super(message);
    }
}

package br.com.attornatus.peopleManagement.exception;

public class SomeOneElseIsAddressException extends RuntimeException {
    public SomeOneElseIsAddressException(String message){
        super(message);
    }
}

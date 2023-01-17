package br.com.attornatus.peopleManagement.exception;

public class SomeoneElseIsAddressException extends RuntimeException {
    public SomeoneElseIsAddressException(String message){
        super(message);
    }
}

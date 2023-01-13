package br.com.attornatus.peopleManagement.Util;

import br.com.attornatus.peopleManagement.exception.InvalidNumberOfCharactersException;
import br.com.attornatus.peopleManagement.exception.nullAddressException;
import br.com.attornatus.peopleManagement.exception.nullAddressFieldException;
import br.com.attornatus.peopleManagement.model.dto.AddressDTO;

public class ValidatorUtil {

    public static boolean validateAddressFields(AddressDTO addressDTO) {

        try {
            checkForNullAddressFields(addressDTO);
            validateNumberOfCharactersAddress(addressDTO);
            return true;
        }catch (InvalidNumberOfCharactersException e ) {
            e.printStackTrace();
        }

        return false;
    }

    private static boolean validateNumberOfCharactersAddress(AddressDTO addressDTO) {

        int numberOfCharacters = addressDTO.getNumber().length();

        if (numberOfCharacters > 5) {
            throw new InvalidNumberOfCharactersException("Quantiadde de caracteres do " +
                    "campo number é maior que 5");
        }
        numberOfCharacters = addressDTO.getZipCode().length();
        if (numberOfCharacters > 9) {
            throw new InvalidNumberOfCharactersException("Quantiadde de caracteres do " +
                    "campo zipCode é maior que 9");
        }

        numberOfCharacters = addressDTO.getStreet().length();
        if (numberOfCharacters > 150) {
            throw new InvalidNumberOfCharactersException("Quantiadde de caracteres do " +
                    "campo street é maior que 150");
        }
        numberOfCharacters = addressDTO.getCity().length();
        if (numberOfCharacters > 50) {
            throw new InvalidNumberOfCharactersException("Quantiadde de caracteres do " +
                    "campo city é maior que 50");
        }

        return true;
    }

    private static boolean checkForNullAddressFields(AddressDTO addressDTO) {
        if (addressDTO == null ){
            throw new nullAddressException("Endereço é nulo");
        }
        if(addressDTO.getStreet() == null){
            throw new nullAddressFieldException("Campo Street do endereço é nulo");
        }
        if(addressDTO.getCity() == null){
            throw new nullAddressFieldException("Campo City do endereço é nulo");
        }
        if(addressDTO.getZipCode() == null){
            throw new nullAddressFieldException("Campo ZipCode do endereço é nulo");
        }
        if(addressDTO.getNumber() == null){
            throw new nullAddressFieldException("Campo Number do endereço é nulo");
        }

        return true;
    }
}

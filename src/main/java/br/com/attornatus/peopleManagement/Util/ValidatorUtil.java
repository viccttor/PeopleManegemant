package br.com.attornatus.peopleManagement.Util;

import br.com.attornatus.peopleManagement.exception.*;
import br.com.attornatus.peopleManagement.model.dto.AddressDTO;
import br.com.attornatus.peopleManagement.model.dto.PersonDTO;

public class ValidatorUtil {

    public static boolean validateAddressFields(AddressDTO addressDTO) {
        return checkForNullAddressFields(addressDTO)
                && checksIfTheFieldHascharactersInvalid(addressDTO.getNumber())
                && checksIfTheFieldHascharactersInvalid(addressDTO.getZipCode())
                && numberOfCharactersAddress(addressDTO);
    }
    public static boolean validatePersonFields(PersonDTO personDTO) {
        return checkForNullPersonFields(personDTO) && numberOfCharactersPerson(personDTO);
    }

    private static boolean checkForNullPersonFields(PersonDTO personDTO) {
        if (personDTO == null ){
            throw new NullPersonException("Objeto PersonDTO é nulo");
        }
        if (personDTO.getName() == null ){
            throw new NullPersonFieldException("Campo Name é nulo");
        }
        if (personDTO.getBirthDate() == null ){
            throw new NullPersonFieldException("Campo birthDate é nulo");
        }

        return true;
    }

    private static boolean numberOfCharactersPerson(PersonDTO personDTO) {

        int numberOfCharacters = personDTO.getName().length();
        if (numberOfCharacters > 100) {
            throw new InvalidNumberOfCharactersException("Quantiade de caracteres do " +
                    "campo name é maior que 100");
        }

        numberOfCharacters = personDTO.getBirthDate().toString().length();
        if (numberOfCharacters > 10) {
            throw new InvalidNumberOfCharactersException("Quantiade de caracteres do " +
                    "campo name é maior que 100");
        }
        return true;
    }

    public static boolean checksIfTheFieldHascharactersInvalid(String fild) {
        if (!fild.matches("^[0-9]+$")) throw new InvalidCharactersException("Neste Campo é " +
                "permitido apenas Números");
        return true;
    }

    private static boolean checkForNullAddressFields(AddressDTO addressDTO) {
        if (addressDTO == null ){
            throw new NullAddressException("Endereço é nulo");
        }
        if(addressDTO.getStreet() == null){
            throw new NullAddressFieldException("Campo Street do endereço é nulo");
        }
        if(addressDTO.getCity() == null){
            throw new NullAddressFieldException("Campo City do endereço é nulo");
        }
        if(addressDTO.getZipCode() == null){
            throw new NullAddressFieldException("Campo ZipCode do endereço é nulo");
        }
        if(addressDTO.getNumber() == null){
            throw new NullAddressFieldException("Campo Number do endereço é nulo");
        }

        return true;
    }

    private static boolean numberOfCharactersAddress(AddressDTO addressDTO) {

        int numberOfCharacters = addressDTO.getNumber().length();

        if (numberOfCharacters > 5) {
            throw new InvalidNumberOfCharactersException("Quantiade de caracteres do " +
                    "campo number é maior que 5");
        }
        numberOfCharacters = addressDTO.getZipCode().length();
        if (numberOfCharacters > 9) {
            throw new InvalidNumberOfCharactersException("Quantiade de caracteres do " +
                    "campo zipCode é maior que 9");
        }

        numberOfCharacters = addressDTO.getStreet().length();
        if (numberOfCharacters > 150) {
            throw new InvalidNumberOfCharactersException("Quantiade de caracteres do " +
                    "campo street é maior que 150");
        }
        numberOfCharacters = addressDTO.getCity().length();
        if (numberOfCharacters > 50) {
            throw new InvalidNumberOfCharactersException("Quantiade de caracteres do " +
                    "campo city é maior que 50");
        }

        return true;
    }


}

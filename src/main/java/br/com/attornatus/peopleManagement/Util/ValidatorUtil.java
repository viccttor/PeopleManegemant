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
            throw new NullPersonException("PersonDTO object is null");
        }
        if (personDTO.getName() == null ){
            throw new NullPersonFieldException("Name field is null in PersonDTO object");
        }
        if (personDTO.getBirthDate() == null ){
            throw new NullPersonFieldException("birthDate field is null in PersonDTO object");
        }

        return true;
    }

    private static boolean numberOfCharactersPerson(PersonDTO personDTO) {

        int numberOfCharacters = personDTO.getName().length();
        if (numberOfCharacters > 100) {
            throw new InvalidNumberOfCharactersException("Number of characters in the name field is " +
                    "greater than 100 in the PersonDTO object");
        }
        return true;
    }

    public static boolean checksIfTheFieldHascharactersInvalid(String fild) {
        if (!fild.matches("^[0-9]+$")) throw new InvalidCharactersException("Only numbers are allowed " +
                "in this field");
        return true;
    }

    private static boolean checkForNullAddressFields(AddressDTO addressDTO) {
        if (addressDTO == null ){
            throw new NullAddressException("AddressDTO object is null");
        }
        if(addressDTO.getStreet() == null){
            throw new NullAddressFieldException("Street field is null in object AddressDTO");
        }
        if(addressDTO.getCity() == null){
            throw new NullAddressFieldException("City field is null in object AddressDTO");
        }
        if(addressDTO.getZipCode() == null){
            throw new NullAddressFieldException("ZipCode field is null in object AddressDTO");
        }
        if(addressDTO.getNumber() == null){
            throw new NullAddressFieldException("Number field is null in object AddressDTO");
        }

        return true;
    }

    private static boolean numberOfCharactersAddress(AddressDTO addressDTO) {

        int numberOfCharacters = addressDTO.getNumber().length();

        if (numberOfCharacters > 5) {
            throw new InvalidNumberOfCharactersException("Number of characters in the number field" +
                    " is greater than 5 in object AddressDTO");
        }
        numberOfCharacters = addressDTO.getZipCode().length();
        if (numberOfCharacters > 9) {
            throw new InvalidNumberOfCharactersException("Number of characters in the zipCode field" +
                    " is greater than 9 in object AddressDTO");
        }

        numberOfCharacters = addressDTO.getStreet().length();
        if (numberOfCharacters > 150) {
            throw new InvalidNumberOfCharactersException("Number of characters in the street field" +
                    " is greater than 150 in object AddressDTO");
        }
        numberOfCharacters = addressDTO.getCity().length();
        if (numberOfCharacters > 50) {
            throw new InvalidNumberOfCharactersException("Number of characters in the city field " +
                    "is greater than 50 in object AddressDTO");
        }

        return true;
    }

    public static boolean validateIfTheIDMatches(long personID, long addressID) {
        if (personID == addressID) {return true;}

        try {
            throw new SomeOneElseIsAddressException("This address does not belong to this user");

        }catch (SomeOneElseIsAddressException e){
            e.printStackTrace();
        }
        return false;
    }
}

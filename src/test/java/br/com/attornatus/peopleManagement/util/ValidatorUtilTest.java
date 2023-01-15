package br.com.attornatus.peopleManagement.util;

import br.com.attornatus.peopleManagement.Util.ValidatorUtil;
import br.com.attornatus.peopleManagement.exception.*;
import br.com.attornatus.peopleManagement.model.dto.AddressDTO;
import br.com.attornatus.peopleManagement.model.dto.PersonDTO;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ErrorCollector;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static br.com.attornatus.peopleManagement.builder.AddressBuilder.newAddress;
import static br.com.attornatus.peopleManagement.builder.PersonBuilder.newPerson;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ValidatorUtilTest {

    @InjectMocks
    private ValidatorUtil util;

    private AddressDTO addressDTO;
    private PersonDTO personDTO;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addressDTO = newAddress().now();
        personDTO = newPerson().now();

    }

    @Order(0)
    @Test
    void nullAddressThrowNullAddressException() {

       addressDTO = null;

       try {
           ValidatorUtil.validateAddressFields(addressDTO);
       }catch (NullAddressException e){
           e.printStackTrace();
           MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("AddressDTO object is null"));
       }
    }

    @Order(1)
    @Test
    void addressValidDontThrowException() {

       ValidatorUtil.validateAddressFields(addressDTO);
       error.checkThat(addressDTO.getNumber(), CoreMatchers.is("221"));
       error.checkThat(addressDTO.getStreet(), CoreMatchers.is("street"));
       error.checkThat(addressDTO.getCity(), CoreMatchers.is("city"));
       error.checkThat(addressDTO.getZipCode(), CoreMatchers.is("11111111"));
       error.checkThat(addressDTO.getPersonID(), CoreMatchers.is(0));

    }

    @Order(2)
    @Test
    void nullStreetFieldEnterNullAddressFieldException() {
       addressDTO = newAddress().setStreet(null).now();try {
           ValidatorUtil.validateAddressFields(addressDTO);
       }catch (NullAddressFieldException e){
           e.printStackTrace();
           MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Street field is null in object AddressDTO"));
       }
    }

    @Order(3)
    @Test
    void nullCityFieldEnterNullAddressFieldException() {
       addressDTO = newAddress().setCity(null).now();

       try {
           ValidatorUtil.validateAddressFields(addressDTO);
       }catch (NullAddressFieldException e){
           e.printStackTrace();
           MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("City field is null in object AddressDTO"));
       }
    }

    @Order(4)
    @Test
    void nullZipCodeFieldEnterNullAddressFieldException() {
       addressDTO = newAddress().setZipCode(null).now();

       try {
           ValidatorUtil.validateAddressFields(addressDTO);
       }catch (NullAddressFieldException e){
           e.printStackTrace();
           MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("ZipCode field is null in object AddressDTO"));
       }
    }

    @Order(5)
    @Test
    void nullNumberFieldEnterNullAddressFieldException() {
        addressDTO = newAddress().setNumber(null).now();

       try {
           ValidatorUtil.validateAddressFields(addressDTO);
       }catch (NullAddressFieldException e){
           e.printStackTrace();
           MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Number field is null in object AddressDTO"));
       }
    }

    @Order(6)
    @Test
    void numberOfCharactersInTheNumberFieldGreaterThanAllowedThrowInvalidNumberOfCharactersException(){
        addressDTO = newAddress().setNumber("123456").now();
        try {
            ValidatorUtil.validateAddressFields(addressDTO);
        }catch (InvalidNumberOfCharactersException e){
            e.printStackTrace();
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Number of characters in the number field" +
                    " is greater than 5 in object AddressDTO"));
        }
    }

    @Order(7)
    @Test
    void numberOfCharactersInTheZipCodeFieldGreaterThanAllowedThrowInvalidNumberOfCharactersException(){
        addressDTO = newAddress().setZipCode("1234567890").now();
        try {
            ValidatorUtil.validateAddressFields(addressDTO);
        }catch (InvalidNumberOfCharactersException e){
            e.printStackTrace();
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Number of characters in the zipCode field" +
                    " is greater than 9 in object AddressDTO"));
        }
    }
    @Order(8)
    @Test
    void numberOfCharactersInTheStreetFieldGreaterThanAllowedThrowInvalidNumberOfCharactersException(){
        addressDTO = newAddress().setStreet("12345678901234567890123456789012345678901234567890" +
                "12345678901234567890123456789012345678901234567890123456789012345678901234567890" +
                "123456789012345678901").now();
        try {
            ValidatorUtil.validateAddressFields(addressDTO);
        }catch (InvalidNumberOfCharactersException e){
            e.printStackTrace();
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Number of characters in the street field" +
                    " is greater than 150 in object AddressDTO"));
        }
    }
    @Order(9)
    @Test
    void numberOfCharactersInTheCityFieldGreaterThanAllowedThrowInvalidNumberOfCharactersException(){
        addressDTO = newAddress().setCity("123456789012345678901234567890123456789012345678901").now();
        try {
            ValidatorUtil.validateAddressFields(addressDTO);
        }catch (InvalidNumberOfCharactersException e){
            e.printStackTrace();
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Number of characters in the city field" +
                    " is greater than 50 in object AddressDTO"));
        }
    }

    @Order(10)
    @Test
    void zipCodeFieldWithUppercaseLettersThrowInvalidCharactersException(){
        try {
            ValidatorUtil.checksIfTheFieldHascharactersInvalid("LETTERS UPPERCASE");
        }catch (InvalidCharactersException e){
            e.printStackTrace();
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Only numbers are allowed in this field"));
        }

    }

    @Order(11)
    @Test
    void zipCodeFieldWithLowercaseLettersThrowInvalidCharactersException(){

        try {
            ValidatorUtil.checksIfTheFieldHascharactersInvalid("letters lowercase");
        }catch (InvalidCharactersException e){
            e.printStackTrace();
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Only numbers are allowed in this field"));
        }

    }

    @Order(12)
    @Test
    void zipCodeFieldWithSpecialCharactersThrowInvalidCharactersException(){

        try {
            ValidatorUtil.checksIfTheFieldHascharactersInvalid("*=@/");
        }catch (InvalidCharactersException e){
            e.printStackTrace();
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Only numbers are allowed in this field"));
        }

    }

    @Order(13)
    @Test
    void personValidDontThrowException() {
        personDTO = newPerson().now();

        ValidatorUtil.validatePersonFields(personDTO);

        error.checkThat(personDTO.getName(), CoreMatchers.is("Victor Henrique"));
        error.checkThat(personDTO.getBirthDate(), CoreMatchers.is(LocalDate.now()));

    }

    @Order(14)
    @Test
    void nullPersonThrowNullPersonException() {

        personDTO = null;

        try {
            ValidatorUtil.validatePersonFields(personDTO);
        } catch (NullPersonException e) {
            e.printStackTrace();
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("PersonDTO object is null"));
        }
    }

    @Order(15)
    @Test
    void nullNameFieldThrowNullPersonFieldException() {

        personDTO = newPerson().setName(null).now();

        try {
            ValidatorUtil.validatePersonFields(personDTO);
        } catch (NullPersonFieldException e) {
            e.printStackTrace();
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Name field is null in PersonDTO object"));
        }
    }

    @Order(16)
    @Test
    void nullBirthDateFieldThrowNullPersonFieldException() {

        personDTO = newPerson().setBirthDate(null).now();

        try {
            ValidatorUtil.validatePersonFields(personDTO);
        } catch (NullPersonFieldException e) {
            e.printStackTrace();
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("birthDate field is null in PersonDTO object"));
        }
    }

    @Order(17)
    @Test
    void invalidAmountOfCharactersInPersonObjectNameFieldThrowsExceptionForInvalidNumberOfCharacters(){
        personDTO = newPerson().setName("Nome 1 Nome 2 Nome 3 Nome 4 Nome 5" +
                "Nome 1 Nome 2 Nome 3 Nome 4 Nome 5" +
                "Nome 1 Nome 2 Nome 3 Nome 4 Nome 5").now();
        try {
            ValidatorUtil.validatePersonFields(personDTO);
        }catch (InvalidNumberOfCharactersException e){
            e.printStackTrace();
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Number of characters in the name field " +
                    "is greater than 100 in the PersonDTO object"));
        }
    }


}
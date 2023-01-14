package br.com.attornatus.peopleManagement.util;

import br.com.attornatus.peopleManagement.Util.ValidatorUtil;
import br.com.attornatus.peopleManagement.exception.InvalidNumberOfCharactersException;
import br.com.attornatus.peopleManagement.exception.NullAddressException;
import br.com.attornatus.peopleManagement.exception.NullAddressFieldException;
import br.com.attornatus.peopleManagement.model.dto.AddressDTO;
import br.com.attornatus.peopleManagement.model.dto.PersonDTO;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static br.com.attornatus.peopleManagement.builder.AddressBuilder.newAddress;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ValidatorUtilTest {

    @InjectMocks
    private ValidatorUtil util;
    private AddressDTO addressDTO;
    private PersonDTO personDTO;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addressDTO = newAddress().now();

    }

    @Order(0)
    @Test
    void nullAddressThrowNullAddressException() {

       addressDTO = null;

       try {
           ValidatorUtil.validateAddressFields(addressDTO);
       }catch (NullAddressException e){
           e.printStackTrace();
           MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Endereço é nulo"));
       }
    }

    @Order(1)
    @Test
    void AddressIsNotNullDontThrowNullAddressException() {

       try {
           ValidatorUtil.validateAddressFields(addressDTO);

       }catch (NullAddressException e){
           e.printStackTrace();
           MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Endereço é nulo"));
       }
    }

    @Order(2)
    @Test
    void nullStreetFieldEnterNullAddressFieldException() {
       addressDTO = newAddress().setStreet(null).now();try {
           ValidatorUtil.validateAddressFields(addressDTO);
       }catch (NullAddressFieldException e){
           e.printStackTrace();
           MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Campo Street do endereço é nulo"));
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
           MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Campo City do endereço é nulo"));
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
           MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Campo ZipCode do endereço é nulo"));
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
           MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Campo Number do endereço é nulo"));
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
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Quantiade de caracteres do " +
                    "campo number é maior que 5"));
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
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Quantiade de caracteres do " +
                    "campo zipCode é maior que 9"));
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
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Quantiade de caracteres do " +
                    "campo street é maior que 150"));
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
            MatcherAssert.assertThat(e.getMessage(), CoreMatchers.is("Quantiade de caracteres do " +
                    "campo city é maior que 50"));
        }
    }

    @Test
    void test(){
        addressDTO = newAddress().setPersonID(null).now();
    }

    //Verificar se no campo cep e no numero se veio letras ou caracteres especiais
    //Verificar se o campo personID é nulo e/ou se tem Letras
}
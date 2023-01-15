package br.com.attornatus.peopleManagement.service;

import br.com.attornatus.peopleManagement.model.Person;
import br.com.attornatus.peopleManagement.model.dto.PersonDTO;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ErrorCollector;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static br.com.attornatus.peopleManagement.builder.PersonBuilder.newPerson;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonServiceTest {

    private PersonService personService = new PersonService();
    private PersonDTO personDTO;
    @Rule
    public ErrorCollector error = new ErrorCollector();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personDTO = newPerson().now();

    }
    @Order(0)
    @Test
    void CreatePersonObjectAndValidateAllFields(){
        Person person = personService.createObjectPerson(personDTO);

        error.checkThat(personDTO.getName(), CoreMatchers.is("Victor Henrique"));
        error.checkThat(personDTO.getBirthDate(), CoreMatchers.is(LocalDate.now()));
        error.checkThat(personDTO.getAddressDTO().getNumber(), CoreMatchers.is("221"));
        error.checkThat(personDTO.getAddressDTO().getStreet(), CoreMatchers.is("street"));
        error.checkThat(personDTO.getAddressDTO().getCity(), CoreMatchers.is("city"));
        error.checkThat(personDTO.getAddressDTO().getZipCode(), CoreMatchers.is("11111111"));
        error.checkThat(personDTO.getAddressDTO().getPersonID(), CoreMatchers.is(0));
    }

}
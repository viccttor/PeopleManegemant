package br.com.attornatus.peopleManagement.service;

import br.com.attornatus.peopleManagement.exception.PersonNotFoundException;
import br.com.attornatus.peopleManagement.model.Person;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
public class PersonServiceTest {


    @Rule
    public ExpectedException exception = ExpectedException.none();
    @MockBean
    private PersonService personService;
    private Person person;

    @BeforeEach
    void setup() {
        person.setName("Victor");
        person.setBirthDate(LocalDate.now());
        person.setId(1);

        BDDMockito.when(personService.findPerson(ArgumentMatchers.anyLong())).thenReturn(person);

    }

    @Test
    public void findId(){
        Person p1 = personService.findPerson(1);

        assertEquals(p1.getId(), person.getId());
    }
    @Test(expected = PersonNotFoundException.class)
    public void PessoaNotFound() {
        exception.expect(PersonNotFoundException.class);
            personService.findPerson(1);
    }

}

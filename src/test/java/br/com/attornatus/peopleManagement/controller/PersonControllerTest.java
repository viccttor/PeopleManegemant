package br.com.attornatus.peopleManagement.controller;

import br.com.attornatus.peopleManagement.service.PersonService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PersonControllerTest {

    @InjectMocks
    private PessoaController controller;
    @Mock
    private PersonService service;

    @Test
    public void test(){

    }

}
package br.com.attornatus.peopleManagement.util;

import br.com.attornatus.peopleManagement.Util.ValidatorUtil;
import br.com.attornatus.peopleManagement.builder.AddressBuilder;
import br.com.attornatus.peopleManagement.exception.nullAddressException;
import br.com.attornatus.peopleManagement.model.dto.AddressDTO;
import br.com.attornatus.peopleManagement.model.dto.PersonDTO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static br.com.attornatus.peopleManagement.builder.AddressBuilder.newAddress;


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

   @Test(expected = nullAddressException.class)
    void validateAddressFields() {
        addressDTO = null;

        ValidatorUtil.validateAddressFields(addressDTO);
    }
    @org.junit.jupiter.api.Test
    void validat(){

    }

}
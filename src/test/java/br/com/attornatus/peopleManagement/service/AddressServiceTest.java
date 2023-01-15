package br.com.attornatus.peopleManagement.service;

import br.com.attornatus.peopleManagement.model.Address;
import br.com.attornatus.peopleManagement.model.dto.AddressDTO;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ErrorCollector;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static br.com.attornatus.peopleManagement.builder.AddressBuilder.newAddress;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService = new AddressService();
    private AddressDTO addressDTO;
    @Rule
    public ErrorCollector error = new ErrorCollector();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        addressDTO = newAddress().now();

    }

    @Order(0)
    @Test
    void CreatePersonObjectAndValidateAllFields() {
        Address address = addressService.creatObjectAddress(addressDTO);

        error.checkThat(address.getNumber(), CoreMatchers.is("221"));
        error.checkThat(address.getStreet(), CoreMatchers.is("street"));
        error.checkThat(address.getCity(), CoreMatchers.is("city"));
        error.checkThat(address.getZipCode(), CoreMatchers.is("11111111"));
        error.checkThat(address.getPersonID(), CoreMatchers.is(0));
    }

}
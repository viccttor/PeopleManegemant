package br.com.attornatus.peopleManagement.service;

import br.com.attornatus.peopleManagement.exception.AddressNotFoundException;
import br.com.attornatus.peopleManagement.model.Address;
import br.com.attornatus.peopleManagement.model.dto.AddressDTO;
import br.com.attornatus.peopleManagement.repository.AddressRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

@Testable
public class AddressServiceTest {

    @Test
    public void test(){

    }

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;

    private Address address = new Address();

    @Test(expected = AddressNotFoundException.class)
    public void enderecoNotFoundException(){
        addressService.findAddressByID(1);
    }

    @Test
    public void newEndereco(){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("Teste");
        addressDTO.setCity("Teste");
        addressDTO.setNumber("11");
        addressDTO.setZipCode("11111-111");
        addressDTO.setPersonID(1l);

        address = addressService.newAddress(addressDTO);

        Assert.assertEquals(addressDTO.getStreet(), address.getStreet());
    }


}

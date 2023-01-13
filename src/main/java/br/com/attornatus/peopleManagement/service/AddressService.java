package br.com.attornatus.peopleManagement.service;

import br.com.attornatus.peopleManagement.Util.ValidatorUtil;
import br.com.attornatus.peopleManagement.exception.AddressNotFoundException;
import br.com.attornatus.peopleManagement.model.Address;
import br.com.attornatus.peopleManagement.model.dto.AddressDTO;
import br.com.attornatus.peopleManagement.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private Address address;
    @Autowired
    private PersonService personService;
    @Autowired
    private AddressRepository addressRepository;

    public Address newAddress(AddressDTO addressDTO) {
        return addressRepository.save(creatObjectAddress(addressDTO));
    }

    public Address creatObjectAddress(AddressDTO addressDTO){
        if(!ValidatorUtil.validateAddressFields(addressDTO)) {
            return null;
        } else {
            address = new Address();
            address.setStreet(addressDTO.getStreet());
            address.setNumber(addressDTO.getNumber());
            address.setZipCode(addressDTO.getZipCode());
            address.setCity(addressDTO.getCity());
            address.setPersonID(addressDTO.getPersonID());

            return address;
        }
    }

    public List<Address> findAddresses(long personID) {
       if(personService.findPerson(personID) == null) {return null;}
        return addressRepository.findAll()
                .stream()
                .filter(e -> e.getPersonID() == personID)
                .collect(Collectors.toList());
    }

    public Address findAddressByID(long AddressID) {
        try {
            address = addressRepository.findById(AddressID).orElse(null);
            if ( address == null) {throw new AddressNotFoundException("Endereço não encontrado");}
        }catch (AddressNotFoundException e) {
            e.printStackTrace();
        }
        return address;
    }
}

package br.com.attornatus.peopleManagement.service;

import br.com.attornatus.peopleManagement.Util.ValidatorUtil;
import br.com.attornatus.peopleManagement.exception.AddressNotFoundException;
import br.com.attornatus.peopleManagement.model.Address;
import br.com.attornatus.peopleManagement.model.dto.AddressDTO;
import br.com.attornatus.peopleManagement.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AddressService {
    private Address address;
    @Autowired
    private PersonService personService;
    @Autowired
    private AddressRepository addressRepository;

    public ResponseEntity<Address> newAddress(AddressDTO addressDTO) {
        address = creatObjectAddress(addressDTO);
        if (address == null
                || personService.findPerson(addressDTO.getPersonID()) == null){
            return new ResponseEntity<Address>(HttpStatus.BAD_REQUEST);
        }

        address = addressRepository.save(address);
        return new ResponseEntity<Address>(address,HttpStatus.CREATED);
    }

    public Address creatObjectAddress(AddressDTO addressDTO){
        boolean itsValid = false;

        try {
          itsValid =  ValidatorUtil.validateAddressFields(addressDTO);

        }catch (RuntimeException e) {
            e.printStackTrace();
        }

        if(!itsValid) {return null;}

        address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setZipCode(addressDTO.getZipCode());
        address.setCity(addressDTO.getCity());
        address.setPersonID(addressDTO.getPersonID());

        return address;

    }

    public ResponseEntity<List<Address>> findAddresses(long personID) {
       if(personService.findPerson(personID) == null) {return new ResponseEntity<List<Address>>(HttpStatus.OK);}

        List<Address> addresses =  addressRepository.findAll()
                .stream()
                .filter(e -> e.getPersonID() == personID)
                .collect(toList());

        return new ResponseEntity<List<Address>>(addresses,HttpStatus.OK);
    }

    public Address findAddressByID(long AddressID) {
        try {
            address = addressRepository.findById(AddressID).orElse(null);
            if (address == null) {throw new AddressNotFoundException("Address not found");}
        }catch (AddressNotFoundException e) {
            e.printStackTrace();
        }
        return address;
    }
}

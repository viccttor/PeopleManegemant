package br.com.attornatus.peopleManagement.service;

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
    private AddressRepository addressRepository;

    public Address newAddress(AddressDTO addressDTO) {
        address = new Address();
        address = creatObjectEndereco(addressDTO);
        addressRepository.save(address);
        
        return address;
    }

    public Address creatObjectEndereco(AddressDTO addressDTO){
        address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setZipCode(addressDTO.getZipCode());
        address.setCity(addressDTO.getCity());
        address.setPersonID(addressDTO.getPersonID());

        return address;
    }

    public List<Address> findAddresses(long PersonID) {
        return addressRepository.findAll()
                .stream()
                .filter(e -> e.getPersonID() == PersonID)
                .collect(Collectors.toList());
    }

    public Address findAddressByID(long AddressID) {
        address = addressRepository.findById(AddressID).orElse(null);
        try {
            if ( address == null) {throw new AddressNotFoundException("Endereço não encontrado");}
        }catch (AddressNotFoundException e) {
            e.printStackTrace();
        }
        return address;
    }
}

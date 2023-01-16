package br.com.attornatus.peopleManagement.controller;

import br.com.attornatus.peopleManagement.model.Address;
import br.com.attornatus.peopleManagement.model.dto.AddressDTO;
import br.com.attornatus.peopleManagement.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/newAddress")
    public ResponseEntity<Address> newAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.newAddress(addressDTO);
    }

    @GetMapping("/findAdresses")
    public ResponseEntity<List<Address> > findAdresses(@RequestParam long PersonID){
        return addressService.findAddresses(PersonID);
    }
}

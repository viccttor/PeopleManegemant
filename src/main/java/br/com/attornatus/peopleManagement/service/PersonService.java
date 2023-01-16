package br.com.attornatus.peopleManagement.service;

import br.com.attornatus.peopleManagement.Util.ValidatorUtil;
import br.com.attornatus.peopleManagement.exception.PersonNotFoundException;
import br.com.attornatus.peopleManagement.model.Address;
import br.com.attornatus.peopleManagement.model.Person;
import br.com.attornatus.peopleManagement.model.dto.PersonDTO;
import br.com.attornatus.peopleManagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private Person person;

    @Autowired
    private AddressService addressService = new AddressService();

    @Autowired
    private PersonRepository personRepository;

    public Person findPerson(long personID){
        person = personRepository.findById(personID).orElse(null);
        try {
            if ( person == null) {throw new PersonNotFoundException("Person not found");}
        }catch (PersonNotFoundException e) {
            e.printStackTrace();
        }
        return person;
    }

    public ResponseEntity<Person> newPerson(PersonDTO personDTO) {
        person = createObjectPerson(personDTO);
        if (person == null) {return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);}
        person = personRepository.save(person);
        setMainAddress(person.getId(), person.getAddress().getId());
        return new ResponseEntity<Person>(person, HttpStatus.CREATED);
    }
    public Person createObjectPerson(PersonDTO personDTO) {
        boolean itsPersonValid = false;
        boolean itsPersonAddressValid = false;

        try {
            itsPersonValid = ValidatorUtil.validatePersonFields(personDTO);
            itsPersonAddressValid =  ValidatorUtil.validateAddressFields(personDTO.getAddressDTO());

        }catch (RuntimeException e) {
            e.printStackTrace();
        }

        if (itsPersonValid && itsPersonAddressValid) {
            person = new Person();
            person.setName(personDTO.getName());
            person.setBirthDate(personDTO.getBirthDate());
            person.setAddress(addressService.creatObjectAddress(personDTO.getAddressDTO()));

            return person;
        }else {
            return null;
        }
    }

    public ResponseEntity<Person> updatePerson(PersonDTO personDTO, long personID){
        person = findPerson(personID);
        if (person == null || personDTO.getAddressDTO().getPersonID() != personID){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        person.setName(personDTO.getName());
        person.setBirthDate(personDTO.getBirthDate());

        Address address = addressService.creatObjectAddress(personDTO.getAddressDTO());
        if (address == null) {return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        address.setId(person.getAddress().getId());

        person.setAddress(address);
        person = personRepository.save(person);

        return new ResponseEntity<Person>(person,HttpStatus.OK);
    }


    public List<Person> listPeople() {
        return personRepository.findAll();
    }

    public ResponseEntity<Person> setMainAddress(long personID, long newPrimaryAddressID) {

        person = findPerson(personID);
        if (person == null ) {return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);}

        Address address = addressService.findAddressByID(newPrimaryAddressID);
        if (address == null) {return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);}

        address.setPersonID(person.getId());
        person.setAddress(address);
        personRepository.save(person);

        return new ResponseEntity<>(person,HttpStatus.OK);
    }
}

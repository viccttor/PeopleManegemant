package br.com.attornatus.peopleManagement.service;

import br.com.attornatus.peopleManagement.Util.ValidatorUtil;
import br.com.attornatus.peopleManagement.exception.PersonNotFoundException;
import br.com.attornatus.peopleManagement.model.Address;
import br.com.attornatus.peopleManagement.model.Person;
import br.com.attornatus.peopleManagement.model.dto.PersonDTO;
import br.com.attornatus.peopleManagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Person newPerson(PersonDTO personDTO) {
        person = personRepository.save(createObjectPerson(personDTO));
        setMainAddress(person.getId(), person.getAddress().getId());
        return person;
    }
    public Person createObjectPerson(PersonDTO personDTO) {
        boolean itsPersonValid = false;
        boolean itsPersonAddressValid = false;
        try {
            itsPersonValid = ValidatorUtil.validatePersonFields(personDTO);


        }catch (RuntimeException e) {
            e.printStackTrace();
        }
        try {
            itsPersonAddressValid =  ValidatorUtil.validateAddressFields(personDTO.getAddressDTO());

        }catch (RuntimeException e) {
            e.printStackTrace();
        }

        if (!itsPersonValid && !itsPersonAddressValid) {
            return null;
        }else {
            person = new Person();
            person.setName(personDTO.getName());
            person.setBirthDate(personDTO.getBirthDate());
            person.setAddress(addressService.creatObjectAddress(personDTO.getAddressDTO()));

            return person;
        }
    }

    public Person updatePerson(PersonDTO personDTO, long personID){
        person = findPerson(personID);
        person.setName(personDTO.getName());
        person.setBirthDate(personDTO.getBirthDate());
        person.setAddress(addressService.creatObjectAddress(personDTO.getAddressDTO()));

        personRepository.save(person);

        return person;
    }


    public List<Person> listPeople() {
        return personRepository.findAll();
    }

    public Person setMainAddress(long personID, long newPrimaryAddressID) {

        person = findPerson(personID);
        Address address = addressService.findAddressByID(newPrimaryAddressID);
        address.setPersonID(personID);
        person.setAddress(address);
        personRepository.save(person);

        return person;
    }
}

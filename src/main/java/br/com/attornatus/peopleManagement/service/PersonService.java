package br.com.attornatus.peopleManagement.service;

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
    private AddressService addressService;

    @Autowired
    private PersonRepository personRepository;

    public Person findPerson(long personID){
        person = personRepository.findById(personID).orElse(null);
        try {
            if ( person == null) {throw new PersonNotFoundException("Person não encontrada!");}
        }catch (PersonNotFoundException e) {
            e.printStackTrace();
        }

        return person;
    }
    public Person newPerson(PersonDTO personDTO) {
        person = new Person();
        person.setName(personDTO.getName());
        person.setBirthDate(personDTO.getBirthDate());
        person.setAddress(addressService.creatObjectEndereco(personDTO.getAddressDTO()));

        personRepository.save(person);
        setMainAddress(person.getId(), person.getAddress().getId());

        return person;
    }

    public Person updatePerson(PersonDTO personDTO, long personID){
        person = findPerson(personID);
        person.setName(personDTO.getName());
        person.setBirthDate(personDTO.getBirthDate());
        person.setAddress(addressService.creatObjectEndereco(personDTO.getAddressDTO()));

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

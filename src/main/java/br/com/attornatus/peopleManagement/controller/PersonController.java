package br.com.attornatus.peopleManagement.controller;

import br.com.attornatus.peopleManagement.model.Person;
import br.com.attornatus.peopleManagement.model.dto.PersonDTO;
import br.com.attornatus.peopleManagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/newPerson")
    public ResponseEntity<Person> newPerson(@RequestBody PersonDTO personDTO) {
        return personService.newPerson(personDTO);
    }

    @PutMapping("/updatePerson")
    public ResponseEntity<Person> updatePerson(@RequestBody PersonDTO personDTO, @RequestParam long id){
        return personService.updatePerson(personDTO, id);
    }

    @PatchMapping("/setMainAddress")
    public ResponseEntity<Person> setMainAddress(@RequestParam long pessoaID, @RequestParam long newAddressID){
        return personService.setMainAddress(pessoaID, newAddressID);
    }

    @GetMapping("/findPerson")
    public ResponseEntity<Person> findPerson(@RequestParam long personID){
        return new ResponseEntity<Person>(personService.findPerson(personID), HttpStatus.OK);
    }

    @GetMapping("/listPeople")
    public ResponseEntity<List<Person>> listPeople(){
        return new ResponseEntity<List<Person>>(personService.listPeople(), HttpStatus.OK);
    }



}

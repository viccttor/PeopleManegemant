package br.com.attornatus.peopleManagement.builder;

import br.com.attornatus.peopleManagement.model.dto.PersonDTO;

import java.time.LocalDate;

public class PersonBuilder {
    private PersonDTO personDTO;

    public PersonBuilder(){}

    public static PersonBuilder newPerson(){
        PersonBuilder builder = new PersonBuilder();
        builder.personDTO.setName("Victor Henrique");
        builder.personDTO.setBirthDate(LocalDate.now());

        return builder;
    }

    public PersonBuilder setBirthDate(LocalDate date){
        personDTO.setBirthDate(date);
        return this;
    }
    public PersonBuilder setName(String name){
        personDTO.setName(name);
        return this;
    }

    public PersonDTO now(){
        return personDTO;
    }
}

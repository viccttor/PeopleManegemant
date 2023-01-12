package br.com.attornatus.peopleManagement.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDTO {

    private String name;
    private LocalDate birthDate;
    private AddressDTO addressDTO;
}

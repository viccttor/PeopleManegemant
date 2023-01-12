package br.com.attornatus.peopleManagement.model.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private String street;
    private String zipCode;
    private String number;
    private String city;

    private long personID;

}

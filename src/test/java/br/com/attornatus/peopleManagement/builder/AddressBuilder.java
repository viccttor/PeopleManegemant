package br.com.attornatus.peopleManagement.builder;

import br.com.attornatus.peopleManagement.model.dto.AddressDTO;

public class AddressBuilder {
    private AddressDTO addressDTO = new AddressDTO();

    public AddressBuilder(){}
    public static AddressBuilder newAddress() {
        AddressBuilder builder = new AddressBuilder();
        builder.addressDTO.setStreet("street");
        builder.addressDTO.setCity("city");
        builder.addressDTO.setZipCode("11111-111");
        builder.addressDTO.setNumber("221");
        builder.addressDTO.setPersonID(0);
        return builder;
    }

    public AddressBuilder setStreet(String street){
        addressDTO.setStreet(street);
        return this;
    }
    public AddressBuilder setCity(String city){
        addressDTO.setCity(city);
        return this;
    }
    public AddressBuilder setZipCode(String zipCode){
        addressDTO.setZipCode(zipCode);
        return this;
    }
    public AddressBuilder setNumber(String number){
        addressDTO.setNumber(number);
        return this;
    }
    public AddressBuilder setPersonID(Long personID){
        addressDTO.setPersonID(personID);
        return this;
    }

    public AddressDTO now(){
       return addressDTO;
    }
}

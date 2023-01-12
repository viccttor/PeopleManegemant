package br.com.attornatus.peopleManagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 150)
    private String street;

    @Column(length = 9)
    private String zipCode;

    @Column(length = 5)
    private String number;
    @Column(length = 50)
    private String city;

    @Column(name="pessoa_id")
    private long personID;
}

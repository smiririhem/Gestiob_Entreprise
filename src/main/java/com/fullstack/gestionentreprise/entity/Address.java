package com.fullstack.gestionentreprise.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Integer id;
    private String streetName;
    private String houseNumber;
    private String zipCode;
@OneToOne
    @JoinColumn (name="employee_id")
    private Employee employee;
}


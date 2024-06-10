package com.hibernate.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import org.hibernate.annotations.NaturalId;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String dni;

    private String name;
    private Double salary;

    public Employee(String dni, String name, Double salary) {
        this.dni = dni;
        this.name = name;
        this.salary = salary;
    }

    public Employee() {
    }

}

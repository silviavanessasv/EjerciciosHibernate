package com.hibernate.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @NaturalId // Por defecto es mutable = false
    @Column(nullable = false, unique = true)
    private String dni;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String email;

    public Customer(String name, String dni, String email) {
        this.name = name;
        this.dni = dni;
        this.email = email;
    }

    public Customer() {
    }

}

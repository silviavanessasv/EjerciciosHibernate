package com.hibernate.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalTime;

@Entity
@Table(
        name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "dni")
        },
        indexes = {
                @Index(columnList = "salary DESC", name = "idx_salary"),
                @Index(columnList = "nss", name = "idx_nss", unique = true),
                @Index(columnList = "check_in, check_out", name = "idx_check_in_out")
        })
@Check(constraints = "check_in < check_out and salary > 0 and age > 18")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 9, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String email;

    private String nss;

    @Column(scale = 2)
    private Double salary;

    @ColumnDefault("18")
    private Integer age;

    @Column(name = "check_in")
    private LocalTime checkIn;

    @Column(name = "check_out")
    private LocalTime checkOut;

    public Employee(String dni, String email, String nss, Double salary, Integer age, LocalTime checkIn, LocalTime checkOut) {
        this.dni = dni;
        this.email = email;
        this.nss = nss;
        this.salary = salary;
        this.age = age;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Employee() {

    }
}

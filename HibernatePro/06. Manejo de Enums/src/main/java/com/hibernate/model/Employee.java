package com.hibernate.model;

import com.hibernate.converter.CategoryEnumConverter;
import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String dni;
    private String name;
    private Integer age;

    //@Enumerated(EnumType.STRING)
    //@Column(columnDefinition = "varchar(30) default 'JUNIOR'")
    @Convert(converter = CategoryEnumConverter.class)
    private EmployeeCategory category;

    public Employee() {
    }

    public Employee(String dni, String name, Integer age, EmployeeCategory category) {
        this.dni = dni;
        this.name = name;
        this.age = age;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", category=" + category +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public EmployeeCategory getCategory() {
        return category;
    }

    public void setCategory(EmployeeCategory category) {
        this.category = category;
    }
}

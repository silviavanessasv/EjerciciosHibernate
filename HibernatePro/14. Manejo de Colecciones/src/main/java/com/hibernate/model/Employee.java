package com.hibernate.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nss;

    @ElementCollection
    private List<String> phones = new ArrayList<>();

    @ElementCollection
    private List<Double> salaries = new ArrayList<>();

    @ElementCollection
    private Set<Double> postalCodes = new HashSet<>();

    @OneToMany // Un empleado puede tener varias tarjetas de crédito, pero una tarjeta de crédito solo a un empleado
    @JoinTable(name = "employee_cards", // Genera una tabla intermedia
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id"))
    @MapKeyColumn(name = "c_key")
    private Map<String, CreditCard> creditCards = new HashMap<>();

    public Employee() {
    }

    public Employee(String name, String nss, List<String> phones, List<Double> salaries) {
        this.name = name;
        this.nss = nss;
        this.phones = new ArrayList<>(phones);
        this.salaries = new ArrayList<>(salaries);
    }

    public Employee(String name, String nss, List<String> phones, List<Double> salaries, Set<Double> postalCodes) {
        this.name = name;
        this.nss = nss;
        this.phones = new ArrayList<>(phones);
        this.salaries = new ArrayList<>(salaries);
        this.postalCodes = new HashSet<>(postalCodes);
    }

    public Employee(String name, String nss, Map<String, CreditCard> creditCards) {
        this.name = name;
        this.nss = nss;
        this.creditCards = new HashMap<>(creditCards);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public List<Double> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Double> salaries) {
        this.salaries = salaries;
    }

    public Map<String, CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Map<String, CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public Set<Double> getPostalCodes() {
        return postalCodes;
    }

    public void setPostalCodes(Set<Double> postalCodes) {
        this.postalCodes = postalCodes;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nss='" + nss + '\'' +
                ", phones=" + phones +
                ", salaries=" + salaries +
                ", postalCodes=" + postalCodes +
                ", creditCards=" + creditCards +
                '}';
    }
}

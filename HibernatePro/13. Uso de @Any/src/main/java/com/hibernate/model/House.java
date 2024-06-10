package com.hibernate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class House implements Item<House> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String location;

    private Double price;

    public House() {
    }

    public House(long id, String location, Double price) {
        this.id = id;
        this.location = location;
        this.price = price;
    }

    public House(String location, Double price) {
        this.location = location;
        this.price = price;
    }

    @Override
    public House getValue() {
        return new House(this.id, this.location, this.price);
    }

    @Override
    public String getName() {
        return this.location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", price=" + price +
                '}';
    }
}

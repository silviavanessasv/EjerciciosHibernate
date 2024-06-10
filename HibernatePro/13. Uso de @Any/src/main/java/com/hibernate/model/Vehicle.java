package com.hibernate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle implements Item<Vehicle> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String manufacturer;
    private String model;

    private Double price;

    public Vehicle() {
    }

    public Vehicle(long id, String manufacturer, String model, Double price) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
    }

    public Vehicle(String manufacturer, String model, Double price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.price = price;
    }

    @Override
    public Vehicle getValue() {
        return new Vehicle(this.id, this.manufacturer, this.model, this.price);
    }

    @Override
    public String getName() {
        return this.manufacturer + " " + this.model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}

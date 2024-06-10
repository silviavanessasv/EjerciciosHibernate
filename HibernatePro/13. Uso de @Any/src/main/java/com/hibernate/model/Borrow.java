package com.hibernate.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyDiscriminator;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyKeyJavaClass;

@Entity
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String concept;

    @Any
    @AnyDiscriminator(DiscriminatorType.CHAR) // Tipo de dato que se usará para discriminar
    @AnyDiscriminatorValue(discriminator = "V", entity = Vehicle.class) // Valor del discriminador y la entidad a la que hace referencia
    @AnyDiscriminatorValue(discriminator = "H", entity = House.class)
    @AnyKeyJavaClass(Long.class) // Tipo de dato que se usará para discriminar (ID)
    @Column(name = "item_type") // Nombre de la columna que almacenará el valor del discriminador
    @JoinColumn(name = "item_id") // Nombre de la columna que almacenará el valor del ID (Join)
    private Item item;

    public Borrow() {
    }

    public Borrow(long id, String concept, Item item) {
        this.id = id;
        this.concept = concept;
        this.item = item;
    }

    public Borrow(String concept, Item item) {
        this.concept = concept;
        this.item = item;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", concept='" + concept + '\'' +
                ", item=" + item +
                '}';
    }
}

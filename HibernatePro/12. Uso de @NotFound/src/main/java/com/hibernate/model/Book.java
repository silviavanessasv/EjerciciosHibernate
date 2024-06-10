package com.hibernate.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
//    @NotFound(action = NotFoundAction.EXCEPTION) // Obliga a que sea EAGER
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)) // Para que al generar la BBDD no genere una FK
    private Author author;

    public Book() {
    }

    public Book(String tittle, Author author) {
        this.title = tittle;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String gettittle() {
        return title;
    }

    public void settittle(String tittle) {
        this.title = tittle;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", tittle='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}

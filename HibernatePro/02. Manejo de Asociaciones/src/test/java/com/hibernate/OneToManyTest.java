package com.hibernate;

import com.hibernate.model.Address;
import com.hibernate.model.Author;
import com.hibernate.model.Book;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OneToManyTest {

    @Test
    void OneToMany() {
        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        Author author1 = session.find(Author.class, 1L);
        System.out.println(author1.getBooks());

    }

    @Test
    void insertData() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Address address1 = new Address("Street1", "City1", "Country1");
        Address address2 = new Address("Street2", "City2", "Country2");

        Author author1 = new Author("Ramesh", "Rames@gmail.com", LocalDate.of(1990, 1, 1));

        Author author2 = new Author("Ramesh1", "Rames1@gmail.com", LocalDate.of(1991, 2, 2));

        session.persist(address1);
        session.persist(address2);

        author1.setAddress(address1);
        author2.setAddress(address2);

        session.persist(author1);
        session.persist(author2);

        Book book1 = new Book("Book1", 100.0, 100, true, author1);
        Book book2 = new Book("Book2", 200.0, 200, true, author1);
        Book book3 = new Book("Book3", 300.0, 300, true, author1);
        Book book4 = new Book("Book4", 400.0, 400, true, author2);

        session.persist(book1);
        session.persist(book2);
        session.persist(book3);
        session.persist(book4);

        session.getTransaction().commit();

    }
}

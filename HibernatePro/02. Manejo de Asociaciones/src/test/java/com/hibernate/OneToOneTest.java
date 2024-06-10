package com.hibernate;

import com.hibernate.model.Address;
import com.hibernate.model.Author;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OneToOneTest {

    @Test
    void oneToOne() {
        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        Author author1 = session.find(Author.class, 1L);
        System.out.println(author1.getAddress());

        Author author2 = session.find(Author.class, 2L);
        System.out.println(author2.getAddress());
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

        session.getTransaction().commit();

    }
}

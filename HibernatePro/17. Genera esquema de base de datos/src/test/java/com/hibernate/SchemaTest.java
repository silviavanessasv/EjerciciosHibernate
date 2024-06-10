package com.hibernate;

import com.hibernate.model.Address;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SchemaTest {

    @BeforeAll
    static void insertData() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Address address = new Address("street", "city", "zip", "state");

        session.persist(address);

        session.getTransaction().commit();
    }

    @Test
    void name() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.createQuery("from Address", Address.class)
                .list()
                .forEach(System.out::println);

        session.close();
        HibernateUtil.shutdown();

    }
}

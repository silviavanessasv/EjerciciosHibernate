package com.hibernate;

import com.hibernate.model.Address;
import com.hibernate.model.Employee;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class EntityTest {

    @BeforeAll
    static void insertData() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Employee employee = new Employee("11111111A", "email1", "nss1",1000.0, 20,
                LocalTime.of(10, 30), LocalTime.of(19, 30));
        Employee employee2 = new Employee("22222222B", "email2", "nss2",1000.0, 20,
                LocalTime.of(10, 30), LocalTime.of(19, 30));

        session.persist(employee);

        session.getTransaction().commit();
    }

    @Test
    void name() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.createQuery("from Employee ", Employee.class)
                .list()
                .forEach(System.out::println);

        session.close();
        HibernateUtil.shutdown();

    }
}

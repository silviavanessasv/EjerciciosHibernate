package com.hibernate;

import com.hibernate.model.Employee;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HibernateTest {

    private static Session sesion;

    @BeforeAll
    public static void setUp() {
        sesion = HibernateUtil.getSessionFactory().openSession();
    }

    @Test
    void persistTest() {
        sesion.beginTransaction();

        Employee emp1 = new Employee("Oliva", 19);
        Employee emp2 = new Employee("La madre de Calero", 52);

        sesion.persist(emp1);
        sesion.persist(emp2);

        sesion.getTransaction().commit();

    }

    @Test
    void retrive() {
        Employee emp = sesion.find(Employee.class, 2L);

        System.out.println(emp);

    }

    @Test
    void update() {

        Employee emp = sesion.find(Employee.class, 2L);
        emp.setAge(99);

        sesion.beginTransaction();
        sesion.merge(emp);
        sesion.getTransaction().commit();

    }

    @Test
    void delete() {

        Employee emp = sesion.find(Employee.class, 1L);

        sesion.beginTransaction();
        sesion.remove(emp);
        sesion.getTransaction().commit();

    }

    @AfterAll
    public static void tearDown() {
        HibernateUtil.shutdown();
    }

}

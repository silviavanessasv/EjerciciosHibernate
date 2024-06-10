package com.hibernate;

import com.hibernate.model.CreditCard;
import com.hibernate.model.Employee;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsTest {

    @BeforeAll
    public static void insertData() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        // Listas (phones y salaries)
        Employee employee = new Employee("Emp1", "11111", List.of("1111", "2222", "3333"), List.of(1000.0, 2000.0, 3000.0));
        Employee employee2 = new Employee("Emp2", "22222", List.of("4444", "5555", "6666"), List.of(4000.0, 5000.0, 6000.0));

        session.persist(employee);
        session.persist(employee2);

        // Set (postalCodes)
        Employee employee3 = new Employee("Emp3", "33333",
                List.of("7777", "8888", "9999"),
                List.of(7000.0, 8000.0, 9000.0),
                Set.of(12345.0, 67890.0));

        session.persist(employee3);

        // Map of Entity (creditCards)
        CreditCard c1 = new CreditCard("1111-2222-3333-4444", "Emp4");
        CreditCard c2 = new CreditCard("5555-6666-7777-8888", "Emp4");
        CreditCard c3 = new CreditCard("9999-0000-1111-2222", "Emp4");

        session.persist(c1);
        session.persist(c2);
        session.persist(c3);

        Employee employee4 = new Employee("Emp4", "44444", Map.of(
                "Key1", c1,
                "Key2", c2,
                "Key3", c3
        ));

        session.persist(employee4);

        session.getTransaction().commit();

        session.close();
    }

    @Test
    void basicList() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee1 = session.find(Employee.class, 1L);

        System.out.println(employee1);
        System.out.println(employee1.getPhones());
        employee1.getSalaries().stream().reduce(Double::sum).ifPresent(System.out::println);

        session.close();
    }

    @Test
    void basicSet() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee3 = session.find(Employee.class, 3L);

        System.out.println(employee3);
        System.out.println(employee3.getPostalCodes());

        session.close();
    }

    @Test
    void entityType() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee4 = session.find(Employee.class, 4L);

        System.out.println(employee4);
        employee4.getCreditCards().values().forEach(System.out::println);
        session.close();

    }

}

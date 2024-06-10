package com.hibernate;

import com.hibernate.model.Customer;
import com.hibernate.model.Employee;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class NaturalIdTest {

    @BeforeAll
    public static void insertData() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Employee employee = new Employee("11111111A", "John Doe", 1000.0);
        Employee employee2 = new Employee("22222222B", "Jane Doe", 2000.0);
        Employee employee3 = new Employee("33333333C", "Paul Doe", 3000.0);

        session.persist(employee);
        session.persist(employee2);
        session.persist(employee3);

        Customer customer1 = new Customer("John Doe", "11111111A", "cust1@gmail.com");
        Customer customer2 = new Customer("Jane Doe", "22222222B", "cust2@gmail.com");
        Customer customer3 = new Customer("Paul Doe", "33333333C", "cust3@gmail.com");

        session.persist(customer1);
        session.persist(customer2);
        session.persist(customer3);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void employeeTest() {
        Session session = HibernateUtil.getSessionFactory().openSession();

//        Employee employee = session.find(Employee.class, 1L);

//        Caso valido cuando se tiene uno o varios naturals ids
//        Employee employee = session.byNaturalId(Employee.class)
//                .using("dni", "11111111A")
//                .load();

        //Caso valido cuando se tiene un SOLO natural id
        Employee employee = session.bySimpleNaturalId(Employee.class)
                .getReference("11111111A");

        System.out.println(employee);

        session.close();
    }

    @Test
    void customerTest() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Si tiene varios naturals ids hay que usar los dos using
        Optional<Customer> customer = session.byNaturalId(Customer.class)
                .using("dni", "11111111A")
                .using("email", "cust1@gmail.com")
                .loadOptional();

        if (customer.isPresent()) { // Si Optional tiene un valor
            System.out.println(customer.get()); // Obtenemos el valor
        } else {
            System.out.println("Customer not found");
        }

//        Con SimpleNaturalId no se puede buscar al tener varios naturals ids
//        Customer customer = session.bySimpleNaturalId(Customer.class)
//                .getReference("11111111A");
//        System.out.println(customer);
    }

    @Test
    void multiple() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Devuelve una lista de empleados por ID
        session.byMultipleIds(Employee.class)
                .multiLoad(1L, 2L)
                .forEach(System.out::println);

//        Devuelve una lista de empleados por natural ID (Aun no implementado para Hibernate 6)
//        session.byMultipleNaturalId(Employee.class)
//                .multiLoad("11111111A", "22222222B")
//                .forEach(System.out::println);
    }
}

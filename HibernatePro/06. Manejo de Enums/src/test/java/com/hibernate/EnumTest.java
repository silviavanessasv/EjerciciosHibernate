package com.hibernate;

import com.hibernate.model.Employee;
import com.hibernate.model.EmployeeCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EnumTest {

    @BeforeAll
    public static void setUp() {
        var Session = HibernateUtil.getSessionFactory().openSession();

        Session.beginTransaction();

        var employee1 = new com.hibernate.model.Employee("12345678A", "John Doe", 30, EmployeeCategory.JUNIOR);
        var employee2 = new com.hibernate.model.Employee("87654321B", "Antonio", 19, EmployeeCategory.SENIOR);
        var employee3 = new com.hibernate.model.Employee("12345678C", "John Smith", 50, EmployeeCategory.MANAGER);
        var employee4 = new com.hibernate.model.Employee("12345678D", "Calero", 2, EmployeeCategory.C_LEVEL);

        Session.persist(employee1);
        Session.persist(employee2);
        Session.persist(employee3);
        Session.persist(employee4);

        Session.getTransaction().commit();
    }

    @Test
    void checkEnum() {
        var Session = HibernateUtil.getSessionFactory().openSession();

        var emp1 = Session.find(Employee.class, 1L);
        System.out.println(emp1);
    }

    @Test
    void findByCategory() {
        var Session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "select e from Employee e where e.category = :category";
        var query = Session.createQuery(jpql, Employee.class);
        query.setParameter("category", EmployeeCategory.JUNIOR);

        query.getResultList().forEach(System.out::println);
    }

    @Test
    void findByCategoryStatic() {
        var Session = HibernateUtil.getSessionFactory().openSession();
        //Da error pero funciona porque lo interpreta automaticamente
        //String jpql = "select e from Employee e where e.category = JUNIOR";
        //var query = Session.createQuery(jpql, Employee.class);

        //query.getResultList().forEach(System.out::println);
    }
}

package com.hibernate;

import com.hibernate.model.Address;
import com.hibernate.model.Author;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;


public class QueriesTest {

    @BeforeAll
    public static void setup() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //Insertamos los Address
        Address address1 = new Address("Calle 1", "Madrid", "España");
        Address address2 = new Address("Calle 2", "Barcelona", "España");
        Address address3 = new Address("Calle 3", "Madrid", "España");

        session.persist(address1);
        session.persist(address2);
        session.persist(address3);

        // Insertamos los Author con sus Address
        Author author1 = new Author("John", "Doe", LocalDate.of(1990, 1, 1));
        author1.setAddress(address1);
        Author author2 = new Author("Calero", "Chapa", LocalDate.of(122, 1, 1));
        author2.setAddress(address2);
        Author author3 = new Author("Jose", "Porro", LocalDate.of(1323, 1, 1));
        author3.setAddress(address3);

        session.persist(author1);
        session.persist(author2);
        session.persist(author3);

        session.getTransaction().commit();
        session.close();
    }
    @Test
    void findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "SELECT a FROM Author a";

        Query<Author> query = session.createQuery(jpql, Author.class);
        query.getResultList().forEach(System.out::println);
        session.close();

    }

    @Test
    void findByEmail() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "SELECT a FROM Author a WHERE a.email = :email";

        Query<Author> query = session.createQuery(jpql, Author.class);
        query.setParameter("email", "porro");

        Author author = query.getSingleResult();
        System.out.println(author);

        session.close();
    }

    @Test
    void findByDates() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "SELECT a FROM Author a WHERE a.birthDate BETWEEN :startDate AND :endDate";

        Query<Author> query = session.createQuery(jpql, Author.class);
        query.setParameter("startDate", LocalDate.of(1320, 1, 1));
        query.setParameter("endDate", LocalDate.of(1330, 1, 1));

        query.getResultList().forEach(System.out::println);
        session.close();
    }

    @Test
    void findByIdIn() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "SELECT a FROM Author a WHERE a.id IN (:ids)";

        Query<Author> query = session.createQuery(jpql, Author.class);
        query.setParameter("ids", List.of(1L, 3L));

        query.getResultList().forEach(System.out::println);
        session.close();
    }

    @Test
    void findByAddressCity() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "SELECT a FROM Author a WHERE a.address.city = :city";

        Query<Author> query = session.createQuery(jpql, Author.class);
        query.setParameter("city", "Madrid");

        query.getResultList().forEach(System.out::println);
        session.close();
    }

    @Test
    void count() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "SELECT count(a.id) FROM Author a";

        long count = session.createQuery(jpql, Long.class).getSingleResult();

        System.out.println(count);

        session.close();
    }

    @Test
    void update() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String jpql = "UPDATE Author a set a.name = :name WHERE a.id = :id";

        session.beginTransaction();

        int updated = session.createMutationQuery(jpql)
                .setParameter("name", "Juan")
                .setParameter("id", 1L)
                .executeUpdate();

        System.out.println(updated);

        session.getTransaction().commit();

        session.close();
    }



}

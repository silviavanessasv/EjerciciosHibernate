package com.hibernate;

import com.hibernate.model.Author;
import com.hibernate.model.Book;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class CriteriaTest {

    @BeforeAll
    public static void init() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Author author1 = new Author("Author1", "Author1@gmail.com", LocalDate.of(1990, 1, 1));
        Author author2 = new Author("Author2", "Author2@hotmail.com", LocalDate.of(1995, 1, 1));
        Author author3 = new Author("Author3", "Author3@gmail.com", LocalDate.of(1990, 1, 1));

        session.persist(author1);
        session.persist(author2);
        session.persist(author3);

        Book book1 = new Book("Book1", 19.99, 450, true, author1);
        Book book2 = new Book("Book2", 29.99, 350, true, author1);
        Book book3 = new Book("Book3", 39.99, 250, true, author2);

        session.persist(book1);
        session.persist(book2);
        session.persist(book3);

        session.getTransaction().commit();
    }

    @Test
    void findAll() { // Select * From author
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);

        Root<Author> root = criteriaQuery.from(Author.class);
        criteriaQuery.select(root);

        session.createQuery(criteriaQuery).getResultList().forEach(System.out::println);

    }

    @Test
    void findById() { // Select * From author Where id = 1
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);

        Root<Author> root = criteriaQuery.from(Author.class);
        criteriaQuery.select(root)
                .where(
                        builder.equal(root.get("id"), 1)
                );

        System.out.println(session.createQuery(criteriaQuery).getSingleResult());

    }

    @Test
    void findByEmailLike() { // Select * From author Where email Like '%@gmail'
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);

        Root<Author> root = criteriaQuery.from(Author.class);
        criteriaQuery.select(root)
                .where(
                        builder.like(root.get("email"), "%@gmail.com")
                );

        session.createQuery(criteriaQuery).getResultList().forEach(System.out::println);

    }

    @Test
    void findByPriceGreaterThan() { // Select * From book Where price > 20.00
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        criteriaQuery.select(root)
                .where(
                        builder.greaterThan(root.get("price"), 20.00)
                );

        session.createQuery(criteriaQuery).getResultList().forEach(System.out::println);

    }

    @Test
    void findBetween() { // Select * From author Where birthDate Between '1989-01-01' and '1993-01-01'
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = builder.createQuery(Author.class);

        Root<Author> root = criteriaQuery.from(Author.class);
        criteriaQuery.select(root)
                .where(builder.between(
                                root.get("birthDate"),
                                LocalDate.of(1989, 1, 1),
                                LocalDate.of(1993, 1, 1)
                        )
                );

        session.createQuery(criteriaQuery).getResultList().forEach(System.out::println);

    }

    @Test
    void findByMultipleWhere() { // Select * From book Where price >= 25 and numberOfPages <= 400
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        var priceGTe50 = builder.greaterThanOrEqualTo(root.get("price"), 25);
        var numPagesLTe400 = builder.lessThanOrEqualTo(root.get("numberOfPages"), 400);
        criteriaQuery.select(root)
                .where(
                        builder.and(priceGTe50, numPagesLTe400)
                );

        session.createQuery(criteriaQuery).getResultList().forEach(System.out::println);

    }

    @Test
    void multiSelect() { // Select title, price From book
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
        Root<Book> root = criteriaQuery.from(Book.class);

        criteriaQuery.multiselect(root.get("title"), root.get("price"));

        List<Object[]> resultList = session.createQuery(criteriaQuery).getResultList();

        for (Object[] objects : resultList) {
            System.out.println("Title: " + objects[0] + ", Price: " + objects[1]);
        }

    }

}

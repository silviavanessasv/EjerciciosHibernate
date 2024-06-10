package com.hibernate;

import com.hibernate.model.Address;
import com.hibernate.model.Author;
import com.hibernate.model.Book;
import com.hibernate.model.Category;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ManyToManyTest {

    @Test
    void manyToMany() {
        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        Book book1 = session.find(Book.class, 1L);
        System.out.println(book1.getCategories());

        Book book3 = session.find(Book.class, 2L);
        System.out.println(book3.getCategories());
    }

    @Test
    void insertData() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Book book1 = new Book("Book1", 100.0, 100, true, null);
        Book book2 = new Book("Book2", 200.0, 200, true, null);
        Book book3 = new Book("Book3", 300.0, 300, true, null);
        Book book4 = new Book("Book4", 400.0, 400, true, null);

        Category category1 = new Category("Category1", 16);
        Category category2 = new Category("Category2", 18);
        Category category3 = new Category("Category3", 20);
        Category category4 = new Category("Category4", 22);

        session.persist(category1);
        session.persist(category2);
        session.persist(category3);
        session.persist(category4);

        book1.getCategories().add(category1);
        book1.getCategories().add(category2);

        book2.getCategories().add(category1);
        book2.getCategories().add(category3);

        book3.getCategories().add(category1);

        session.persist(book1);
        session.persist(book2);
        session.persist(book3);
        session.persist(book4);

        session.getTransaction().commit();

    }
}

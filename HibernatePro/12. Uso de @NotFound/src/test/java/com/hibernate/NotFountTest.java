package com.hibernate;

import com.hibernate.model.Author;
import com.hibernate.model.Book;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class NotFountTest {

    @Test
    void notFoundTest() {
        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        Book book1 = session.find(Book.class, 1L);

        System.out.println("================");
        System.out.println(book1.getAuthor());
    }
    
    void insertData(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Author author1 = new Author("Author 1");
        Author author2 = new Author("Author 2");
        Author author3 = new Author("Author 3");
        
        session.persist(author1);
        session.persist(author2);

        Book book1 = new Book("Book 1", author1);
        Book book2 = new Book("Book 2", author1);
        Book book3 = new Book("Book 3", author1);

        session.persist(book1);
        session.persist(book2);
        session.persist(book3);

        session.remove(author1);

        session.getTransaction().commit();
        session.close();
    }
}

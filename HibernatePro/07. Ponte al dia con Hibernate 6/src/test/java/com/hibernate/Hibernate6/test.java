package com.hibernate.Hibernate6;

import com.hibernate.HibernateUtil;
import com.hibernate.model.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

public class test {

    @Test
    void name() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Deprecated
        session.createQuery("select b from Book b");

        //Ahora es necesario indicar el tipo de retorno
        Query<Book> query = session.createQuery("select b from Book b", Book.class);
    }
}

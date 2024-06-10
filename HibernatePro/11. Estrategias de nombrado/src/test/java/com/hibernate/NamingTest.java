package com.hibernate;

import com.hibernate.model.SmartPhone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class NamingTest {

    @BeforeAll
    public static void insertData() {
        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var smartPhone1 = new SmartPhone("Samsung", "1234567890");
        var smartPhone2 = new SmartPhone("Apple", "0987654321");
        var smartPhone3 = new SmartPhone("Xiaomi", "1230984567");

        session.persist(smartPhone1);
        session.persist(smartPhone2);
        session.persist(smartPhone3);

        session.getTransaction().commit();
    }

    @Test
    public void name() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var smartPhone = session.find(SmartPhone.class, 1L);
        System.out.println(smartPhone);
    }
}

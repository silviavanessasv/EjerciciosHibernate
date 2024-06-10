package com.hibernate;

import com.hibernate.model.Borrow;
import com.hibernate.model.House;
import com.hibernate.model.Vehicle;
import com.mysql.cj.xdevapi.SessionImpl;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AnyTest {

    @BeforeAll
    static void insertData() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        House house = new House("Madrid", 100000.0);
        House house2 = new House("Barcelona", 200000.0);

        session.persist(house);
        session.persist(house2);

        Vehicle vehicle = new Vehicle("Ford", "Focus", 20000.0);
        Vehicle vehicle2 = new Vehicle("Audi", "A3", 30000.0);

        session.persist(vehicle);
        session.persist(vehicle2);

        Borrow borrow1 = new Borrow( "Borrow house", house);
        Borrow borrow2 = new Borrow( "Borrow house2", house2);
        Borrow borrow3 = new Borrow( "Borrow vehicle", vehicle);
        Borrow borrow4 = new Borrow( "Borrow vehicle2", vehicle2);

        session.persist(borrow1);
        session.persist(borrow2);
        session.persist(borrow3);
        session.persist(borrow4);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    void any() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Borrow borrow = session.find(Borrow.class, 1); // House
        System.out.println(borrow.getItem().getName());

        Borrow borrow2 = session.find(Borrow.class, 3);// Vehicle
        System.out.println(borrow2.getItem().getName());

        session.close();
    }
}

package com.hibernate;

import com.hibernate.model.Address;
import com.hibernate.model.Author;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

/*
1.Transient (Cuando no ha sido gestionada por el Sesion)
2. Managed (Cuando ha sido gestionada por el Sesion)
3. Detached (Cuando ha sido gestionada por el Sesion y luego se ha cerrado la sesion)
4. Removed (Cuando ha sido eliminada por el Sesion)

 */
public class LifeCycleTest {

    @Test
    void transientState() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address address = new Address("Calle 1", "Madrid", "España");
        System.out.println(session.contains(address)); // false (No esta gestionada por el Sesion aun)

        /* Cuando no es managed puede generar problemas al asociarla a una entidad

        Author author = new Author("Nombre", "email", null);
        author.setAddress(address);

        session.beginTransaction();
        session.persist(author);
        session.getTransaction().commit();

        TransientObjectException: object references an unsaved transient instance */

    }

    @Test
    void managed() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address address = new Address("Calle 1", "Madrid", "España");

        session.beginTransaction();
        session.persist(address);
        session.getTransaction().commit();

        System.out.println(session.contains(address)); // false (No esta gestionada por el Sesion aun)
    }

    @Test
    void detached(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address address = new Address("Calle 2", "Update2", "España Update");
        address.setId(1L);

        session.beginTransaction();
        //session.persist(address);
        //EntityExistsException: detached entity passed to persist
        session.merge(address);
        session.getTransaction().commit();
    }

    @Test
    void detached2(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        //Forma incorrecta
        Address address = new Address("Calle 2", "Update3", "España Update");
        address.setId(1L);

        session.beginTransaction();
        //session.persist(address);
        //EntityExistsException: detached entity passed to persist

        //Forma correcta
        address = session.find(Address.class, 1L);
        address.setCity("Update4");
        session.persist(address);

        session.getTransaction().commit();

        // Para que sea detached (No gestionada por el Sesion)
        session.detach(address);
    }

    @Test
    void removed(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address address = new Address();
        address.setId(1L); // El unico campo que necesita para borrarla

        session.beginTransaction();
        session.remove(address); // Lo borra aunque no este gestionada por el Sesion
        session.getTransaction().commit();
    }

    @Test
    void differentSessions(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address address = session.find(Address.class, 2L);
        System.out.println(session.contains(address)); // true

        session.close();

        session = HibernateUtil.getSessionFactory().openSession();
        System.out.println(session.contains(address)); // false
    }

    @Test
    void load(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Address address = session.getReference(Address.class, 2L); // No carga la entidad hasta que se accede a un campo
        System.out.println("=======");
        //Si cierra la sesion antes de acceder a un campo, da error porque aun no se ha cargado la entidad
        System.out.println(address.getCountry());

        Address address2 = session.find(Address.class, 2L); // Carga la entidad al momento
        System.out.println("=======");
        System.out.println(address.getCountry());

    }


}

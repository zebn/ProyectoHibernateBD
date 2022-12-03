package es.ua.eps.hibernate.querying;
import java.util.Set;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import es.ua.eps.hibernate.model.Usuario;
import es.ua.eps.hibernate.model.Conexion;
import es.ua.eps.hibernate.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class Ejercicio5 {


    static int id_usuario = 99;

    public static void main (String [] args) {

        Ejercicio5.q2();
        Ejercicio5.q11();
        Ejercicio5.q13();



    }

    private static void q2() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("E5 Q2. Session factory creada");
        session.beginTransaction();
        Query query = session.getNamedQuery("selectId").setParameter("id_usuario", id_usuario);
        List<Date> dates = query.list();
        for (Date date : dates) {
            System.out.println("Conn date: " + date);
        }
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();

    }

    private static void q11() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Session factory creada");
        session.beginTransaction();
        Query query = session.getNamedQuery("selectPremium");
        query.setParameter("id_perfil", 2);
        List<Conexion> conexiones = query.list();
        for (Conexion conexion : conexiones) {
            System.out.println("Connections Premium:  " + conexion.getMomento_entrada());
        }
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }



        private static void q13() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("E5 Q13. Session factory creada");
        session.beginTransaction();
        org.hibernate.query.Query query = session.getNamedQuery("deleteRows").setParameter("id_perfil", 0);
        int deletedRows = query.executeUpdate();
        System.out.println("Deleted profiles: " + deletedRows);
        session.getTransaction().rollback();
        HibernateUtil.getSessionFactory().close();

    }



}

package es.ua.eps.hibernate.querying;

import es.ua.eps.hibernate.model.Conexion;
import es.ua.eps.hibernate.model.Usuario;
import es.ua.eps.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio2 {

    static int id_usuario = 99;

    public static void main (String [] args) {

            Ejercicio2.q8();
            Ejercicio2.q9();


    }

    private static void q8() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Q9. Session factory creada");
        session.beginTransaction();
        //no he hecho
        String hql = "SELECT c FROM Conexion c WHERE id_usuario = :id_usuario";
        org.hibernate.query.Query query = session.createQuery(hql).setParameter("id_usuario",id_usuario);
        List<Conexion> listconexiones  = query.list();
        System.out.println("User num.99 connections number: "+listconexiones);
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }



    private static void q9() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Q9. Session factory creada");
        session.beginTransaction();
        String hql = "SELECT c FROM Conexion c WHERE id_usuario = :id_usuario";
        org.hibernate.query.Query query = session.createQuery(hql).setParameter("id_usuario",id_usuario);
        List<Conexion> listconexiones  = query.list();
        System.out.println("User num.99 connections number: "+listconexiones.size());
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }


}

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


public class Ejercicio3 {

    static int id_usuario = 99;

    public static void main (String [] args) {

        Ejercicio3.q10();
        Ejercicio3.q11();


    }
    private static void q10() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Q10. Session factory creada");
        session.beginTransaction();
        List<Conexion> listconexiones = new ArrayList<Conexion>();
        listconexiones = session.createNativeQuery("select * from Conexion c JOIN Usuario u ON c.id_usuario = u.id_usuario WHERE id_perfil = 2", Conexion.class).list();

        for (Conexion conexion : listconexiones) {
            System.out.println("Connections Premiums SQL:  " + conexion.getMomento_entrada());
        }


        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }



        private static void q11() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Session factory creada");
        session.beginTransaction();
        String hql = "select c from Conexion c where c.usuario.perfil.id_perfil = :id_perfil";
        org.hibernate.query.Query query = session.createQuery(hql).setParameter("id_perfil", 2);
        List<Conexion> conexiones = query.list();
        for (Conexion conexion : conexiones) {
            System.out.println( "Connections Premiums HQL:  " + conexion.getMomento_entrada());
        }
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();



    }


}

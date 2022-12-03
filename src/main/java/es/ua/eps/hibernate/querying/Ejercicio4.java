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


public class Ejercicio4 {


    static int id_usuario = 99;

    public static void main (String [] args) {

        Ejercicio4.q13();
    //    Ejercicio4.q14();


    }

    private static void q13() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Session factory creada");
        session.beginTransaction();
        String hql = "delete from Conexion c where c.usuario.id_usuario in(select u.id_usuario from Usuario u where u.perfil.id_perfil in(select p.id_perfil from Perfil p where p.id_perfil = :id_perfil))";
        org.hibernate.query.Query query = session.createQuery(hql).setParameter("id_perfil", 0);
        int deletedRows = query.executeUpdate();
        System.out.println("Deleted: "+ deletedRows);
        session.getTransaction().rollback();
        HibernateUtil.getSessionFactory().close();




    }

}

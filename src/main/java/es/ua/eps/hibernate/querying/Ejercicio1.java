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


public class Ejercicio1 {

    static int id_usuario = 99;

    public static void main (String [] args) {

        Ejercicio1.q1();
        Ejercicio1.q2();
        Ejercicio1.q3();
        Ejercicio1.q4();
        Ejercicio1.q5();
        Ejercicio1.q6();
        Ejercicio1.q7();

    }

    private static void q1 () {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Q1. Session factory creada");
        session.beginTransaction();
        Usuario user = session.get(Usuario.class, id_usuario);
        Set<Conexion> conexiones = user.getConexionset();
        for(Conexion conexion : conexiones)
        {
            System.out.println("User was connected:  " + conexion.getMomento_entrada().toString());
        }
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    private static void q2() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Q2. Session factory creada");
        session.beginTransaction();
        List<Object> listconexiones = new ArrayList<Object>();
        listconexiones = session.createNativeQuery("SELECT momento_entrada FROM dbo.conexion WHERE id_usuario = 99 ORDER BY momento_entrada DESC ").list();
        for (Object conexion : listconexiones) {
            System.out.println("User was connected:: "+conexion);
        }
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();

    }

    private static void q3() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Q3. Session factory creada");
        session.beginTransaction();
        List<Conexion> listconexiones = new ArrayList<Conexion>();
        listconexiones = session.createNativeQuery("SELECT * FROM dbo.conexion WHERE momento_entrada BETWEEN '1966-11-01' AND '2021-11-30' ", Conexion.class).list();
        for (Conexion conexion : listconexiones) {
            Usuario user = conexion.getUsuario();
            System.out.println("Connection:"+conexion.getMomento_entrada()+" "+user.getApellidos()+" "+user.getNombre()+" "+user.getPerfil().getDescripcion());
        }

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    private static void q4() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Q4. Session factory creada");
        session.beginTransaction();
        org.hibernate.query.Query<Date> query = session.createQuery("SELECT momento_entrada FROM Conexion WHERE id_usuario =:id_usuario ORDER BY momento_entrada DESC").setParameter("id_usuario",id_usuario);
        List<Date> listconexiones = query.list();
        for(Date conexion : listconexiones) {
            System.out.println("HQL Query:  " + conexion);
        }
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    private static void q5() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Q5. Session factory creada");
        session.beginTransaction();
        String hql = "SELECT c FROM Conexion c WHERE id_usuario = :id_usuario AND momento_entrada > '1966-03-01'";
        org.hibernate.query.Query query = session.createQuery(hql).setParameter("id_usuario",id_usuario);
        List<Conexion> listconexiones  = query.list();
        for( Conexion conexion : listconexiones)
        {
            System.out.println("HQL " + conexion.getMomento_entrada());
        }
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    private static void q6() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Q6. Session factory creada");
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
        Root<Conexion> root = criteria.from(Conexion.class);
        Path<Long> idPath = root.get("usuario");
        Path<Date> datePath = root.get("momento_entrada");
        criteria.multiselect(datePath);
        criteria.where(builder.equal(root.get("usuario"), id_usuario));
        List<Tuple> tuples = session.createQuery(criteria).list();
        for(Tuple tuple : tuples)
        {
            System.out.println("Tupla:  " + tuple.get(datePath));
        }
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    private static void q7() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Q7. Session factory creada");
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Conexion> criteria = builder.createQuery(Conexion.class);
        Root<Conexion> root = criteria.from(Conexion.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("usuario"), id_usuario));
        List<Conexion> conexiones = session.createQuery(criteria).list();
        for(Conexion conexion : conexiones)
        {
            System.out.println("Connection :  " + conexion.getMomento_entrada());
        }
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }


}

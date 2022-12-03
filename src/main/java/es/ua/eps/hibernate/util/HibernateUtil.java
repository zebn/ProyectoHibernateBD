package es.ua.eps.hibernate.util;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

    //SessionFactory para mapeo XML
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            // Cargamos la configuración de hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Configuration loaded");

            //El ServiceReistry controla las solicitudes de acceso a la base de datos
            ServiceRegistry serviceRegistry = new
                    StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            System.out.println("Hibernate serviceRegistry created");

            //La SessionFactory estiona la conexión a la base de datos
            SessionFactory sessionFactory =
                    configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null)
            sessionFactory = buildSessionFactory();
        else if (sessionFactory.isClosed())
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}

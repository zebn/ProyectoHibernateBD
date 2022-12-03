package es.ua.eps.hibernate.main;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.ajbrown.namemachine.Name;
import org.ajbrown.namemachine.NameGenerator;
import org.hibernate.Session;

import es.ua.eps.hibernate.model.Conexion;
import es.ua.eps.hibernate.model.InformacionPublica;
import es.ua.eps.hibernate.model.Perfil;
import es.ua.eps.hibernate.model.Usuario;
import es.ua.eps.hibernate.util.HibernateUtil;

public class HibernateMain {

    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator();
        List<Name> names = generator.generateNames( 1000 );
        Random rnd = new Random();

        //Get Session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Session factory creada");
        List<Integer> createdUserIds = new LinkedList<Integer>();

        //Adding 1000 users
        session.beginTransaction();
        for(Name n: names) {
            Usuario user=new Usuario();
            user.setNombre(n.getFirstName());
            user.setApellidos(n.getLastName());
            user.setEmail(n.getFirstName().toLowerCase()+"."+n.getLastName().toLowerCase()+"@dlsi.ua.es");
            user.setPassword(Long.toHexString(Double.doubleToLongBits(Math.random())).getBytes());
            user.setApodo(null);
            int perfil_id = rnd.nextInt(3);
            Perfil perfil = session.get(Perfil.class, perfil_id);
            user.setPerfil(perfil);
            user.setNacido(new Date(-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000))));
            session.persist(user);
            createdUserIds.add(user.getUsuId());
        }

        session.getTransaction().commit();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        for(int idUser: createdUserIds) {
            System.out.println(idUser);
            InformacionPublica info=new InformacionPublica();
            Usuario curUser = session.get(Usuario.class,idUser);
            info.setUsuario(curUser);
            info.setMostrar_email(Math.random() < 0.5);
            info.setMostrar_nacido(Math.random() < 0.5);
            info.setMostrar_nombre(Math.random() < 0.5);
            session.save(info);
        }

        session.getTransaction().commit();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        //Setting connections between users
        List<Integer> randomCreatedUserIds = new LinkedList<Integer>(createdUserIds);
        Collections.shuffle(createdUserIds);
        for(int idUser: createdUserIds) {
            Usuario curUser = session.get(Usuario.class,idUser);
            //Users follow up to 30 people
            int totalFollowing = rnd.nextInt(30) + 1;
            int startingPos = rnd.nextInt(970) +  1;
            Set<Usuario> followed = new HashSet<Usuario>();
            for(int idx: randomCreatedUserIds.subList(startingPos, startingPos+totalFollowing)) {
                Usuario fUser = session.get(Usuario.class,idx);
                followed.add(fUser);
            }
            curUser.setSiguea(followed);
            session.update(curUser);
        }

        session.getTransaction().commit();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        //Create connections
        for(int idUser: createdUserIds) {
            Usuario curUser = session.get(Usuario.class,idUser);
            //Users have up to 100 connections
            for(int connection=rnd.nextInt(100) + 1; connection>0; connection--) {
                Date connectionTime=new Date(-146771200000L + (Math.abs(rnd.nextLong()) % (2L * 365 * 24 * 60 * 60 * 1000)));
                Conexion con = new Conexion();
                con.setMomento_entrada(connectionTime);
                con.setIdusuario(curUser);
                session.save(con);
            }
        }

        session.getTransaction().commit();
        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().close();
    }
}
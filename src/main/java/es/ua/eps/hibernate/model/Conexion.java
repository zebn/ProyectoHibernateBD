package es.ua.eps.hibernate.model;
import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQueries({
        @NamedQuery(name ="selectId",query = "SELECT momento_entrada FROM Conexion WHERE id_usuario =:id_usuario ORDER BY momento_entrada DESC"),
        @NamedQuery(name = "selectPremium", query ="select c from Conexion c where c.usuario.perfil.id_perfil = :id_perfil"),
        @NamedQuery(name = "deleteRows", query = "delete from Conexion c where c.usuario.id_usuario in(select u.id_usuario from Usuario u where u.perfil.id_perfil in(select p.id_perfil from Perfil p where p.id_perfil = :id_perfil))")
})


@Table(name = "conexion")
public class Conexion implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "momento_entrada", nullable = false)
    private java.util.Date momento_entrada;


    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;


    public Usuario getUsuario() {
        return usuario;
    }

    public void setIdusuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public java.util.Date getMomento_entrada() {
        return momento_entrada;
    }

    public void setMomento_entrada(java.util.Date momento_entrada) {
        this.momento_entrada = momento_entrada;
    }

}

package es.ua.eps.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "informacion_publica")
public class InformacionPublica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "mostrar_email", nullable = true)
    private boolean mostrar_email;

    @Column(name = "mostrar_nacido", nullable = true)
    private boolean mostrar_nacido;

    @Column(name = "mostrar_nombre", nullable = true)
    private boolean mostrar_nombre;
    @OneToOne
    @Id
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public boolean getMostrar_email() {
        return mostrar_email;
    }

    public void setMostrar_email(boolean mostrar_email) {
        this.mostrar_email = mostrar_email;
    }


    public boolean getMostrar_nacido() {
        return mostrar_nacido;
    }

    public void setMostrar_nacido(boolean mostrar_nacido) {
        this.mostrar_nacido = mostrar_nacido;
    }

    public boolean getMostrar_nombre() {
        return mostrar_nombre;
    }

    public void setMostrar_nombre(boolean mostrar_nombre) {
        this.mostrar_nombre = mostrar_nombre;
    }


}

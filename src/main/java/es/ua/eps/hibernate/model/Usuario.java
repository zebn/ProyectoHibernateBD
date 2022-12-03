package es.ua.eps.hibernate.model;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;

@Entity
@Table(name="usuario")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", unique = true, nullable = false)
    private int id_usuario;

    @Column(name = "password", nullable = true)
    private byte[] password;

    @Column(name = "apodo", nullable = true)
    private String apodo;

    @Column(name = "email")
    private String email;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "nacido", nullable = false)
    private java.util.Date nacido;

    @OneToMany(mappedBy = "usuario")
    private Set<Conexion> conexionset;

    public Set<Conexion> getConexionset() {
        return conexionset;
    }

    public void setConexionset(Set<Conexion> conexionset) {
        this.conexionset = conexionset;
    }

    @ManyToOne
    @JoinColumn(name = "id_perfil", nullable = false)
    private Perfil perfil;

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }


    @OneToOne(mappedBy = "usuario")
    private InformacionPublica informacionPublica;

    public InformacionPublica getInformacionPublica() {
        return informacionPublica;
    }

    public void setInformacionPublica(InformacionPublica informacionPublica) {
        this.informacionPublica = informacionPublica;
    }


    @ManyToMany(mappedBy = "seguidores")
    private Set<Usuario> seguidos;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sigue", joinColumns = @JoinColumn(name = "id_seguido"),
            inverseJoinColumns = @JoinColumn(name = "id_seguidor"))
    private Set<Usuario> seguidores;


    public int getUsuId() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getEmail() {
        return apodo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public java.util.Date getNacido() {
        return nacido;
    }

    public void setNacido(java.util.Date nacido) {
        this.nacido = nacido;
    }


    public void setSiguea(Set<Usuario> followed) {
        this.seguidos = followed;
        for (Usuario user : followed) {
            user.getSeguidores().add(this);
        }


    }

    private Set<Usuario> getSeguidores() {
        return seguidores;
    }


}

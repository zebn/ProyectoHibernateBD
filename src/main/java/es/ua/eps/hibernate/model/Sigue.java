package es.ua.eps.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name = "sigue")

public class Sigue {

    @Column(name = "id_seguidor")

    private int id_seguidor;

    @Column(name = "id_seguido")

    private int id_seguido;


}
package com.campusjalpa.instauaz.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@ToString
@EqualsAndHashCode
public class Usuario {

    @Id
    @Getter @Setter @Column(name = "id_usuario")
    private int id_usuario;

    @Getter @Setter @Column(name = "nom_usuario")
    private String nom_usuario;

    @Getter @Setter @Column(name = "con_usuario")
    private String con_usuario;

}

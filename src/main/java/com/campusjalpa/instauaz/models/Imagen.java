package com.campusjalpa.instauaz.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "imagenes")
@ToString
@EqualsAndHashCode
public class Imagen {
    // Constructor de la clase Imagen
    public Imagen(int imagen_id, String imagen_info, String imagen_url, int id_usuario) {
        this.imagen_id = imagen_id;
        this.imagen_info = imagen_info;
        this.imagen_url = imagen_url;
        this.id_usuario = id_usuario;
    }

    @Id
    @Getter @Setter
    @Column(name = "imagen_id")
    private int imagen_id;

    @Getter @Setter
    @Column(name = "imagen_info")
    private String imagen_info;

    @Getter @Setter
    @Column(name = "imagen_url")
    private String imagen_url;

    @Getter @Setter
    @Column(name = "id_usuario")
    private int id_usuario;

    // Constructor vacío de la clase Imagen
    public Imagen() {
    }
}

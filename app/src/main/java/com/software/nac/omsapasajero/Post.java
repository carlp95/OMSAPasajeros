package com.software.nac.omsapasajero;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Neury on 11/2/2017.
 */

public class Post {


    private Integer id;

    private Long fechaPublicada;

    private Integer numeroDePuntuacion;

    private String comentario;

    public Post() {
    }

    public Post(Long fechaPublicada, Integer numeroDePuntuacion, String comentario) {
        this.fechaPublicada = fechaPublicada;
        this.numeroDePuntuacion = numeroDePuntuacion;
        this.comentario = comentario;
    }

    public Post(Integer id, Long fechaPublicada, Integer numeroDePuntuacion, String comentario) {
        this.id = id;
        this.fechaPublicada = fechaPublicada;
        this.numeroDePuntuacion = numeroDePuntuacion;
        this.comentario = comentario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getFechaPublicada() {
        return fechaPublicada;
    }

    public void setFechaPublicada(Long fechaPublicada) {
        this.fechaPublicada = fechaPublicada;
    }

    public Integer getNumeroDePuntuacion() {
        return numeroDePuntuacion;
    }

    public void setNumeroDePuntuacion(Integer numeroDePuntuacion) {
        this.numeroDePuntuacion = numeroDePuntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", fechaPublicada=" + fechaPublicada +
                ", numeroDePuntuacion=" + numeroDePuntuacion +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}

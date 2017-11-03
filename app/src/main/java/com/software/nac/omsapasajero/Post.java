package com.software.nac.omsapasajero;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Neury on 11/2/2017.
 */

public class Post {
    @SerializedName("fechaPublicada")
    @Expose
    private Long fechaPublicada;

    @SerializedName("numeroDePuntuacion")
    @Expose
    private Integer numeroDePuntuacion;

    @SerializedName("comentario")
    @Expose
    private String comentario;

    @SerializedName("id")
    @Expose
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "fechaPublicada=" + fechaPublicada +
                ", numeroDePuntuacion=" + numeroDePuntuacion +
                ", comentario='" + comentario + '\'' +
                ", id=" + id +
                '}';
    }
}

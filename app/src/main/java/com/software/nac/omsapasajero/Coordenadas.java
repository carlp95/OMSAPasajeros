package com.software.nac.omsapasajero;

import java.io.Serializable;

/**
 * Created by Neury on 8/1/2017.
 */

public class Coordenadas implements Serializable {
    private Long id;
    private String latitude;
    private String longitud;
    private String habilitado;

    public Coordenadas() {
    }

    public Coordenadas(Long id, String latitude, String longitud, String habilitado) {
        this.id = id;
        this.latitude = latitude;
        this.longitud = longitud;
        this.habilitado = habilitado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }
}

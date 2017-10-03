package com.software.nac.omsapasajero;

import java.io.Serializable;

/**
 * Created by Neury on 8/1/2017.
 */

public class Coordenadas implements Serializable {
    private Long id;
    private String latitude;
    private String longitud;

    public Coordenadas() {
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

    @Override
    public String toString() {
        return "Coordenadas{" +
                "id=" + id +
                ", latitude='" + latitude + '\'' +
                ", longitud='" + longitud + '\'' +
                '}';
    }
}

package com.software.nac.omsapasajero;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Neury on 8/25/2017.
 */

public class Ruta implements Serializable {
    private int id;
    private String distanciaTotal;
    private String fechaCreada;
    private String fechaUltimaModificacion;
    private String ciudad;
    private String nombreCorredor;
    private String esDireccionSubida;
    private String habilitado;
    private Coordenadas[] coordenadas;
    private String paradas; // esto debe llegar null hay que cambiar el Json


    public Ruta() {

    }

    public Ruta(int id, String distanciaTotal, String fechaCreada, String fechaUltimaModificacion, String ciudad, String nombreCorredor, String esDireccionSubida, String habilitado, Coordenadas[] coordenadas, String paradas) {
        this.id = id;
        this.distanciaTotal = distanciaTotal;
        this.fechaCreada = fechaCreada;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.ciudad = ciudad;
        this.nombreCorredor = nombreCorredor;
        this.esDireccionSubida = esDireccionSubida;
        this.habilitado = habilitado;
        this.coordenadas = coordenadas;
        this.paradas = paradas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(String distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public String getFechaCreada() {
        return fechaCreada;
    }

    public void setFechaCreada(String fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    public String getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombreCorredor() {
        return nombreCorredor;
    }

    public void setNombreCorredor(String nombreCorredor) {
        this.nombreCorredor = nombreCorredor;
    }

    public String getEsDireccionSubida() {
        return esDireccionSubida;
    }

    public void setEsDireccionSubida(String esDireccionSubida) {
        this.esDireccionSubida = esDireccionSubida;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Coordenadas[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getParadas() {
        return paradas;
    }

    public void setParadas(String paradas) {
        this.paradas = paradas;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "id=" + id +
                ", distanciaTotal='" + distanciaTotal + '\'' +
                ", fechaCreada='" + fechaCreada + '\'' +
                ", fechaUltimaModificacion='" + fechaUltimaModificacion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", nombreCorredor='" + nombreCorredor + '\'' +
                ", esDireccionSubida='" + esDireccionSubida + '\'' +
                ", habilitado='" + habilitado + '\'' +
                ", coordenadas=" + Arrays.toString(coordenadas) +
                ", paradas='" + paradas + '\'' +
                '}';
    }
}

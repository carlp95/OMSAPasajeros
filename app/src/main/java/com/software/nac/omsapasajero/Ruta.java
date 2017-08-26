package com.software.nac.omsapasajero;

/**
 * Created by Neury on 8/25/2017.
 */

public class Ruta {
    private String id;
    private String distanciaTotal;
    private String fechaCreada;
    private String fechaUltimaModificacion;
    private String ciudad;
    private String nombreCorredor;
    private String esDireccionSubida;
    private Coordenadas coordenadas;
    private String paradas; // esto debe llegar null hay que cambiar el Json


    public Ruta() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getParadas() {
        return paradas;
    }

    public void setParadas(String paradas) {
        this.paradas = paradas;
    }
}

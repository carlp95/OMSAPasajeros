package com.software.nac.omsapasajero;

/**
 * Created by Neury on 8/25/2017.
 */

public class UltimaParada {
    private String id;
    private String nombre;
    private String ruta; // esto hay que enviar un null cambiar el JSON
    private String paradaAnterior;
    private String paradaSiguiente;
    private Coordenadas coordenada;


    public UltimaParada() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getParadaAnterior() {
        return paradaAnterior;
    }

    public void setParadaAnterior(String paradaAnterior) {
        this.paradaAnterior = paradaAnterior;
    }

    public String getParadaSiguiente() {
        return paradaSiguiente;
    }

    public void setParadaSiguiente(String paradaSiguiente) {
        this.paradaSiguiente = paradaSiguiente;
    }

    public Coordenadas getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenadas coordenada) {
        this.coordenada = coordenada;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}

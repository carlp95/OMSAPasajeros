package com.software.nac.omsapasajero;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

/**
 * Created by Neury on 8/1/2017.
 */

public class APIParadas implements Serializable {
    private String id;
    private String nombre;
    private String ruta;
    private String paradaAnterior;
    private String paradaSiguiente;
    private Coordenadas coordenada;
    private String habilitado;

    public APIParadas() {
    }

    public APIParadas(String id, String nombre, String ruta, String paradaAnterior, String paradaSiguiente, Coordenadas coordenada, String habilitado) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
        this.paradaAnterior = paradaAnterior;
        this.paradaSiguiente = paradaSiguiente;
        this.coordenada = coordenada;
        this.habilitado = habilitado;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
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

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public String toString() {
        return "APIParadas{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ruta='" + ruta + '\'' +
                ", paradaAnterior='" + paradaAnterior + '\'' +
                ", paradaSiguiente='" + paradaSiguiente + '\'' +
                ", coordenada=" + coordenada +
                ", habilitado='" + habilitado + '\'' +
                '}';
    }
}

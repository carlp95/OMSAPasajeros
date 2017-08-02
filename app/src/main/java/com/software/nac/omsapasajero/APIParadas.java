package com.software.nac.omsapasajero;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

/**
 * Created by Neury on 8/1/2017.
 */

public class APIParadas {
    private String id;
    private String nombre;
    private String paradaAnterior;
    private String paradaSiguiente;
    private Coordenadas cooredenada;

    public Coordenadas getCooredenada() {
        return cooredenada;
    }

    public void setCooredenada(Coordenadas cooredenada) {
        this.cooredenada = cooredenada;
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






}

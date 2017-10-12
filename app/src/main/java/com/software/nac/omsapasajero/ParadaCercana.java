package com.software.nac.omsapasajero;

import java.io.Serializable;

/**
 * Created by Neury on 10/11/2017.
 */

public class ParadaCercana implements Serializable {
    private String paradaActual;
    private String distancia;
    private String destOrig;
    private BuscarParadaConRuta paradaSalida;
    private BuscarParadaConRuta paradaLLegada;

    public ParadaCercana() {
    }

    public ParadaCercana(String paradaActual, String distancia, String destOrig, BuscarParadaConRuta paradaSalida, BuscarParadaConRuta paradaLLegada) {
        this.paradaActual = paradaActual;
        this.distancia = distancia;
        this.destOrig = destOrig;
        this.paradaSalida = paradaSalida;
        this.paradaLLegada = paradaLLegada;
    }

    public String getParadaActual() {
        return paradaActual;
    }

    public void setParadaActual(String paradaActual) {
        this.paradaActual = paradaActual;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getDestOrig() {
        return destOrig;
    }

    public void setDestOrig(String destOrig) {
        this.destOrig = destOrig;
    }

    public BuscarParadaConRuta getParadaSalida() {
        return paradaSalida;
    }

    public void setParadaSalida(BuscarParadaConRuta paradaSalida) {
        this.paradaSalida = paradaSalida;
    }

    public BuscarParadaConRuta getParadaLLegada() {
        return paradaLLegada;
    }

    public void setParadaLLegada(BuscarParadaConRuta paradaLLegada) {
        this.paradaLLegada = paradaLLegada;
    }

    @Override
    public String toString() {
        return "ParadaCercana{" +
                "paradaActual='" + paradaActual + '\'' +
                ", distancia='" + distancia + '\'' +
                ", destOrig='" + destOrig + '\'' +
                ", paradaSalida=" + paradaSalida +
                ", paradaLLegada=" + paradaLLegada +
                '}';
    }
}

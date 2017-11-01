package com.software.nac.omsapasajero;

import java.io.Serializable;

/**
 * Created by Neury on 8/25/2017.
 */

public class Autobus implements Serializable {

    private String id;
    private String modelo;
    private String cantidadDeAsientos;
    private String peso;
    private Ruta ruta;
    //private String ultimaParada;
    private UltimaParada ultimaParada;
    private String anoFabricacion;
    private String activo;
    private String conductor;
    private String matricula;
    private String fechaCreada;
    private String ultimaFechaModificada;
    private String precio;
    private String tieneAireAcondicionado;
    private String cantidadDePasajerosActual;
    private Coordenadas coordenada;
    private String raspberryPiNumeroSerial;
    private String habilitado;

    public Autobus() {
    }

    public Autobus(String id, String modelo, String cantidadDeAsientos, String peso, Ruta ruta, UltimaParada ultimaParada, String anoFabricacion, String activo, String conductor, String matricula, String fechaCreada, String ultimaFechaModificada, String precio, String tieneAireAcondicionado, String cantidadDePasajerosActual, Coordenadas coordenada, String raspberryPiNumeroSerial, String habilitado) {
        this.id = id;
        this.modelo = modelo;
        this.cantidadDeAsientos = cantidadDeAsientos;
        this.peso = peso;
        this.ruta = ruta;
        this.ultimaParada = ultimaParada;
        this.anoFabricacion = anoFabricacion;
        this.activo = activo;
        this.conductor = conductor;
        this.matricula = matricula;
        this.fechaCreada = fechaCreada;
        this.ultimaFechaModificada = ultimaFechaModificada;
        this.precio = precio;
        this.tieneAireAcondicionado = tieneAireAcondicionado;
        this.cantidadDePasajerosActual = cantidadDePasajerosActual;
        this.coordenada = coordenada;
        this.raspberryPiNumeroSerial = raspberryPiNumeroSerial;
        this.habilitado = habilitado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCantidadDeAsientos() {
        return cantidadDeAsientos;
    }

    public void setCantidadDeAsientos(String cantidadDeAsientos) {
        this.cantidadDeAsientos = cantidadDeAsientos;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public UltimaParada getUltimaParada() {
        return ultimaParada;
    }

    public void setUltimaParada(UltimaParada ultimaParada) {
        this.ultimaParada = ultimaParada;
    }

    public String getAnoFabricacion() {
        return anoFabricacion;
    }

    public void setAnoFabricacion(String anoFabricacion) {
        this.anoFabricacion = anoFabricacion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getFechaCreada() {
        return fechaCreada;
    }

    public void setFechaCreada(String fechaCreada) {
        this.fechaCreada = fechaCreada;
    }

    public String getUltimaFechaModificada() {
        return ultimaFechaModificada;
    }

    public void setUltimaFechaModificada(String ultimaFechaModificada) {
        this.ultimaFechaModificada = ultimaFechaModificada;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTieneAireAcondicionado() {
        return tieneAireAcondicionado;
    }

    public void setTieneAireAcondicionado(String tieneAireAcondicionado) {
        this.tieneAireAcondicionado = tieneAireAcondicionado;
    }

    public String getCantidadDePasajerosActual() {
        return cantidadDePasajerosActual;
    }

    public void setCantidadDePasajerosActual(String cantidadDePasajerosActual) {
        this.cantidadDePasajerosActual = cantidadDePasajerosActual;
    }

    public Coordenadas getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenadas coordenada) {
        this.coordenada = coordenada;
    }

    public String getRaspberryPiNumeroSerial() {
        return raspberryPiNumeroSerial;
    }

    public void setRaspberryPiNumeroSerial(String raspberryPiNumeroSerial) {
        this.raspberryPiNumeroSerial = raspberryPiNumeroSerial;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }


    @Override
    public String toString() {
        return "Autobus{" +
                "id='" + id + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cantidadDeAsientos='" + cantidadDeAsientos + '\'' +
                ", peso='" + peso + '\'' +
                ", ruta=" + ruta +
                ", ultimaParada=" + ultimaParada +
                ", anoFabricacion='" + anoFabricacion + '\'' +
                ", activo='" + activo + '\'' +
                ", conductor='" + conductor + '\'' +
                ", matricula='" + matricula + '\'' +
                ", fechaCreada='" + fechaCreada + '\'' +
                ", ultimaFechaModificada='" + ultimaFechaModificada + '\'' +
                ", precio='" + precio + '\'' +
                ", tieneAireAcondicionado='" + tieneAireAcondicionado + '\'' +
                ", cantidadDePasajerosActual='" + cantidadDePasajerosActual + '\'' +
                ", coordenada=" + coordenada +
                ", raspberryPiNumeroSerial='" + raspberryPiNumeroSerial + '\'' +
                ", habilitado='" + habilitado + '\'' +
                '}';
    }
}

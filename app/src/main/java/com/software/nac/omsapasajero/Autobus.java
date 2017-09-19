package com.software.nac.omsapasajero;

/**
 * Created by Neury on 8/25/2017.
 */

public class Autobus {

    private String id;
    private String modelo;
    private String cantidadDeAsientos;
    private String peso;
    private Ruta ruta;
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


    public Autobus() {

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
                '}';
    }
}

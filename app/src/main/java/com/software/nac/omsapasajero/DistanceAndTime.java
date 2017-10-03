package com.software.nac.omsapasajero;

import java.io.Serializable;

/**
 * Created by Neury on 9/19/2017.
 */

public class DistanceAndTime implements Serializable {
    private String distance;
    private String duration;
    private String duration_Traffic;
    private Autobus autobus;


    public DistanceAndTime(String distance, String duration, String duration_Traffic, Autobus autobus) {
        this.distance = distance;
        this.duration = duration;
        this.duration_Traffic = duration_Traffic;
        this.autobus = autobus;
    }

    public DistanceAndTime() {
    }

    public Autobus getAutobus() {
        return autobus;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration_Traffic() {
        return duration_Traffic;
    }

    public void setDuration_Traffic(String duration_Traffic) {
        this.duration_Traffic = duration_Traffic;
    }

    @Override
    public String toString() {
        return "DistanceAndTime{" +
                "distance='" + distance + '\'' +
                ", duration='" + duration + '\'' +
                ", duration_Traffic='" + duration_Traffic + '\'' +
                ", autobus=" + autobus +
                '}';
    }
}

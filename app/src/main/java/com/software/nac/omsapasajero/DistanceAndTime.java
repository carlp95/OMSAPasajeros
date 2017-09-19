package com.software.nac.omsapasajero;

/**
 * Created by Neury on 9/19/2017.
 */

public class DistanceAndTime {
    private String distance;
    private String duration;
    private String duration_Traffic;

    public DistanceAndTime() {
    }

    public DistanceAndTime(String distance, String duration, String duration_Traffic) {
        this.distance = distance;
        this.duration = duration;
        this.duration_Traffic = duration_Traffic;
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
}

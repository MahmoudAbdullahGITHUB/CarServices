package com.example.carserviceapp.data;

import android.location.Location;

import java.io.Serializable;

public class CarModel implements Serializable {

    String model;
    String type;
    double lat;
    double longtu;

    public CarModel() {
    }

    public CarModel(String model, String type, double lat, double longtu) {
        this.model = model;
        this.type = type;
        this.lat = lat;
        this.longtu = longtu;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongtu() {
        return longtu;
    }

    public void setLongtu(double longtu) {
        this.longtu = longtu;
    }
}

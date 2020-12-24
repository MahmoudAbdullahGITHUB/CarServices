package com.example.carserviceapp.data;

public class ClientModel {

    String id;
    String name;
    String password;
    boolean expatriate;
    CarModel clientCarModel;

    public ClientModel(String id, String name, String password, boolean expatriate, CarModel clientCarModel) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.expatriate = expatriate;
        this.clientCarModel = clientCarModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isExpatriate() {
        return expatriate;
    }

    public void setExpatriate(boolean expatriate) {
        this.expatriate = expatriate;
    }

    public CarModel getClientCarModel() {
        return clientCarModel;
    }

    public void setClientCarModel(CarModel clientCarModel) {
        this.clientCarModel = clientCarModel;
    }
}

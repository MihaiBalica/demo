package org.example.objects;

public class CarRequest {
    private String car;
    private String fuel;

    public CarRequest(){}

    public CarRequest(String car, String fuel) {
        this.car = car;
        this.fuel = fuel;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}

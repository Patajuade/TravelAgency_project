package com.example.travelagency.models;

public class PlaneStage extends TripStage {

    int flyingSpeed;

    int waitingTime;

    double pricePerKm;

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public PlaneStage() {
        flyingSpeed = 700;
        waitingTime = 0;
        pricePerKm = 0.025;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public void priceCompute() {
        price = distance * pricePerKm;
        price = (double) Math.round(price * 100) / 100;
    }

    @Override
    public void durationCompute() {
        duration = (waitingTime + (distance * 60/flyingSpeed)) /60;
        duration = (double) Math.round(duration * 100) / 100;
    }

    @Override
    public void updateDatas() {
        distance = destination.distanceCompute(source);
        super.updateDatas();
    }

    public void setFlyingSpeed(int flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }


    public double getPricePerKm() {
        return pricePerKm;
    }

    public int getSpeed() {
        return flyingSpeed;
    }

    public int getWaitingTime() {
        return waitingTime;
    }
}

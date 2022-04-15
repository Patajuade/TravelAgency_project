package com.example.travelagency.models;

public abstract class TripStage {
    protected double price;
    protected double duration;

    public abstract void priceCompute();
    public abstract void durationCompute();

    public double getPrice() {
        return price;
    }

    public double getDuration() {
        return duration;
    }
}

package be.helha.common.models;

import java.io.Serializable;

/**
 * Model for TripStage
 * Mother class of HotelStage and PlaneStage
 */
public abstract class TripStage implements Serializable {
    protected double price;
    protected double duration;
    protected double distance;

    protected CityModel source;
    protected CityModel destination;

    /**
     * abstract method redefined in children classes
     */
    public abstract void priceCompute();

    /**
     * abstract method redefined in children classes
     */
    public abstract void durationCompute();

    /**
     * Getter allowing to use source outside this class.
     * @return a starting city
     */
    public CityModel getSource() {
        return source;
    }

    /**
     * Getter allowing to use pricePerNights outside this class.
     * @return a city of destination
     */
    public CityModel getDestination() {
        return destination;
    }

    /**
     * Getter allowing to use price outside this class.
     * @return a price value
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter allowing to use duration outside this class.
     * @return a duration value
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Setter allowing to set a new value for the destination city
     * @param destination is the city we are heading to
     */
    public void setDestination(CityModel destination){
        this.destination = destination;
    }

    /**
     * Setter allowing to set a new value for source
     * @param source is the city from which the trip starts
     */
    public void setSource(CityModel source) {
        this.source = source;
    }

    /**
     * Setter allowing to set a new value for price
     * @param price of the trip
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Setter allowing to set a new value for duration
     * @param duration of the trip
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * Setter allowing to set a new value for distance.
     * @param distance between airports
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Calls methods that calculates price and duration
     * allows to recalculate both at the same time
     */
    public void updateDatas() {
        priceCompute();
        durationCompute();
    }
}

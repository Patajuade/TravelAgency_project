package com.example.travelagency.models;

/**
 * <p>Model for hotel objects</p>
 * inherits of TripStage class
 */
public class HotelStage extends TripStage {
    int numberOfNights;
    int pricePerNight;

    /**
     * <p>default constructor</p>
     */
    public HotelStage() {
        distance = 0;
    }

    /**
     * <p>Calculates the stay price by multiplying the price per night and the number of nights.</p>
     *Overrides method in the mother class.
     */
    @Override
    public void priceCompute() {
        price = pricePerNight * numberOfNights;
    }

    /**
     * <p>Calculates the number of days for the hotel stay, by multiplying the number of nights by 24.</p>
     * Overrides method in the mother class.
     */
    @Override
    public void durationCompute() {
        duration = numberOfNights * 24;
    }

    /**
     * <p>Getter allowing to use numberOfNihts outside of this class.</p>
     * @return number of nights spent at the hotel
     */
    public int getNumberOfNights() {
        return numberOfNights;
    }

    /**
     * <p>Getter allowing to use pricePerNights outside of this class.</p>
     * @return price that a night at the hotel costs
     */
    public int getPricePerNight() { return pricePerNight; }

    /**
     * <p>Setter allowing to set a new value for numberOfNights.</p>
     * @param numberOfNights is the number of nights.
     */
    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    /**
     * <p>Setter allowing to set a new value for pricePerNight.</p>
     * @param pricePerNight is the price per night.
     */
    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

}

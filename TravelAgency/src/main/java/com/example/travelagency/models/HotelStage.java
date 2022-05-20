package com.example.travelagency.models;

/**
 *
 */
public class HotelStage extends TripStage {
    int numberOfNights;
    int pricePerNight;

    /**
     *
     */
    public HotelStage() {
        distance = 0;
    }

    /**
     *
     */
    @Override
    public void priceCompute() {
        price = pricePerNight * numberOfNights;
    }

    /**
     *
     */
    @Override
    public void durationCompute() {
        duration = numberOfNights * 24;
    }

    /**
     * @return
     */
    public int getNumberOfNights() {
        return numberOfNights;
    }

    /**
     * @return
     */
    public int getPricePerNight() { return pricePerNight; }

    /**
     * @param numberOfNights
     */
    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    /**
     * @param pricePerNight
     */
    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

}

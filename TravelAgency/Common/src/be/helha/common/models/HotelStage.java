package be.helha.common.models;

/**
 * Model for hotel objects
 * inherits of TripStage class
 */
public class HotelStage extends TripStage {
    int numberOfNights;
    int pricePerNight;

    /**
     * default constructor
     */
    public HotelStage() {
        distance = 0;
    }

    /**
     * Calculates the stay price by multiplying the price per night and the number of nights.
     *Overrides method in the mother class.
     */
    @Override
    public void priceCompute() {
        price = pricePerNight * numberOfNights;
    }

    /**
     * Calculates the number of days for the hotel stay, by multiplying the number of nights by 24.
     * Overrides method in the mother class.
     */
    @Override
    public void durationCompute() {
        duration = numberOfNights * 24;
    }

    /**
     * Getter allowing to use numberOfNihts outside this class.
     * @return number of nights spent at the hotel
     */
    public int getNumberOfNights() {
        return numberOfNights;
    }

    /**
     * Getter allowing to use pricePerNights outside this class.
     * @return price that a night at the hotel costs
     */
    public int getPricePerNight() { return pricePerNight; }

    /**
     * Setter allowing to set a new value for numberOfNights.
     * @param numberOfNights is the number of nights.
     */
    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    /**
     * Setter allowing to set a new value for pricePerNight.
     * @param pricePerNight is the price per night.
     */
    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

}

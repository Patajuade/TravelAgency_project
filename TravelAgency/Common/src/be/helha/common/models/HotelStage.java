package be.helha.common.models;

public class HotelStage extends TripStage {

    int numberOfNights;

    int pricePerNight;

    public HotelStage() {
        distance = 0;
    }

    @Override
    public void priceCompute() {
        price = pricePerNight * numberOfNights;
    }
    @Override
    public void durationCompute() {
        duration = numberOfNights * 24;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public int getPricePerNight() { return pricePerNight; }

    public void setNumberOfNights(int numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

}

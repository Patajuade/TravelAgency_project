package com.example.travelagency.models;

/**
 * Model for plane objects
 * inherits of TripStage class
 */
public class PlaneStage extends TripStage {

    int flyingSpeed;

    int waitingTime;

    double pricePerKm;


    /**
     * Default constructor
     */
    public PlaneStage() {
        flyingSpeed = 700;
        waitingTime = 0;
        pricePerKm = 0.025;
    }

    /**
     * Calculates the fight price, using distance and pricePerKm
     * Overrides method in the mother class.
     */
    @Override
    public void priceCompute() {
        price = distance * pricePerKm;
        price = (double) Math.round(price * 100) / 100;
    }

    /**
     * Calculates the fight duration, using distance, waitingTime, flyingSpeed and duration
     * Overrides method in the mother class.
     */
    @Override
    public void durationCompute() {
        duration = (waitingTime + (distance * 60/flyingSpeed)) /60;
        duration = (double) Math.round(duration * 100) / 100;
    }

    /**
     * Updates the distance and the price when informations are changed
     */
    @Override
    public void updateDatas() {
        if(destination != null){
            distance = destination.distanceCompute(source);
        }
        super.updateDatas();
    }

    /**
     * Setter allowing to set a new value for distance.
     * @param distance distance between airports
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     *  Setter allowing to set a new value for flying speed.
     * @param flyingSpeed speed at which the plane flies
     */
    public void setFlyingSpeed(int flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    /**
     *  Setter allowing to set a new value for waiting time.
     * @param waitingTime is the duration of an eventual stopover
     */
    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    /**
     *  Setter allowing to set a new value for price per km.
     * @param pricePerKm is the price per km traveled in the plane
     */
    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }


    /**
     * Getter allowing to use distance outside this class.
     * @return distance between the two airports
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Getter allowing to use pricePerKm outside this class.
     * @return price per km traveled in the plane
     */
    public double getPricePerKm() {
        return pricePerKm;
    }

    /**
     * Getter allowing to use flyingSpeed outside this class.
     * @return speed at which the plan is flying
     */
    public int getSpeed() {
        return flyingSpeed;
    }

    /**
     * Getter allowing to use waiting time outside this class.
     * @return durattion of an eventual stopover
     */
    public int getWaitingTime() {
        return waitingTime;
    }
}

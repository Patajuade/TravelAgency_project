package com.example.travelagency.models;

/**
 * <p> Model for plane objects</>
 * inherits of TripStage class
 */
public class PlaneStage extends TripStage {

    int flyingSpeed;

    int waitingTime;

    double pricePerKm;


    /**
     * <p>Default constructor </>
     */
    public PlaneStage() {
        flyingSpeed = 700;
        waitingTime = 0;
        pricePerKm = 0.025;
    }

    /**
     * <p>Calculates the fight price, using distance and pricePerKm </p>
     * Overrides method in the mother class.
     */
    @Override
    public void priceCompute() {
        price = distance * pricePerKm;
        price = (double) Math.round(price * 100) / 100;
    }

    /**
     * <p>Calculates the fight duration, using distance, waitingTime, flyingSpeed and duration</p>
     * Overrides method in the mother class.
     */
    @Override
    public void durationCompute() {
        duration = (waitingTime + (distance * 60/flyingSpeed)) /60;
        duration = (double) Math.round(duration * 100) / 100;
    }

    /**
     * <p>Updates the distance and the price when informations are changed</p>
     */
    @Override
    public void updateDatas() {
        if(destination != null){
            distance = destination.distanceCompute(source);
        }
        super.updateDatas();
    }

    /**
     * <p>Setter allowing to set a new value for distance. </>
     * @param distance distance between airports
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     *  <p>Setter allowing to set a new value for flying speed. </p>
     * @param flyingSpeed speed at which the plane flies
     */
    public void setFlyingSpeed(int flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    /**
     *  <p>Setter allowing to set a new value for waiting time. </p>
     * @param waitingTime is the duration of an eventual stopover
     */
    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    /**
     *  <p>Setter allowing to set a new value for price per km. </p>
     * @param pricePerKm is the price per km traveled in the plane
     */
    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }


    /**
     * <p>Getter allowing to use distance outside of this class. </>
     * @return distance between the two airports
     */
    public double getDistance() {
        return distance;
    }

    /**
     * <p>Getter allowing to use pricePerKm outside of this class. </>
     * @return price per km traveled in the plane
     */
    public double getPricePerKm() {
        return pricePerKm;
    }

    /**
     * <p>Getter allowing to use flyingSpeed outside of this class. </>
     * @return speed at which the plan is flying
     */
    public int getSpeed() {
        return flyingSpeed;
    }

    /**
     * <p>Getter allowing to use waiting time outside of this class. </>
     * @return durattion of an eventual stopover
     */
    public int getWaitingTime() {
        return waitingTime;
    }
}

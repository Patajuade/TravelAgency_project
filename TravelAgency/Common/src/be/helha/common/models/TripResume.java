package com.example.travelagency.models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Model for TripResume
 * TripResume is a resume of a trip (a trip is composed of multiple HotelStages and PlaneStages)
 */
public class TripResume {

    String name;
    LocalDate date;
    ArrayList<TripStage> stages;
    CityModel Source;

    double totalDistance;
    double totalTime;
    double totalPrice;

    /**
     * Default constructor
     */
    public TripResume() {
        this.stages = new ArrayList<>();
    }

    /**
     * Method allowing to add a stage to the trip list
     * @param tripStage is a list of TripStages
     */
    public void addStage(TripStage tripStage){
        stages.add(tripStage);
    }

    /**
     * Method allowing to remove a stage from the trip list
     * @param tripStage is a list of TripStages
     */
    public void removeStage(TripStage tripStage){
        stages.remove(tripStage);
    }

    /**
     * Calculates the total price, duration and distance of a trip
     */
    public void calculateAll(){
        totalTime = 0;
        totalDistance = 0;
        totalPrice = 0;
        for(TripStage tripStage :stages){
            if(tripStage.getDestination() != null && tripStage.getSource() != null){
                totalDistance += tripStage.getDestination().distanceCompute(tripStage.getSource());
            }
            totalTime += tripStage.getDuration();
            totalPrice += tripStage.getPrice();
        }
    }

    /**
     *Updates the source of a stage and updates its datas
     */
    public void updateTripStep(){
        TripStage previousTripStage = null;
        for(TripStage tripStage : getStages()){
            if(tripStage instanceof PlaneStage){
                if(previousTripStage == null){
                    tripStage.setSource(this.getSource());
                }
                else{
                    tripStage.setSource(previousTripStage.getDestination());
                }
                if(tripStage.getSource() != null){
                    tripStage.updateDatas();
                    previousTripStage = tripStage;
                }
            }
        }
    }

    /**
     * Getter allowing to use the trip's name outside this class.
     * @return the name of the trip
     */
    public String getName() {
        return name;
    }

    /**
     * Getter allowing to use date outside this class.
     * @return the date of the trip
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Getter allowing to use totalDistance outside this class.
     * @return the total distance of the trip
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * Getter allowing to use totalTime outside this class.
     * @return the total time of the trip
     */
    public double getTotalTime() {
        return totalTime;
    }

    /**
     * Getter allowing to use totalPrice outside this class.
     * @return the total price of the trip
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Getter allowing to use source outside this class.
     * @return the starting city of the trip
     */
    public CityModel getSource() {
        return Source;
    }

    /**
     * Getter allowing to use stage outside this class.
     * @return a list of stages
     */
    public ArrayList<TripStage> getStages() {
        return stages;
    }

    /**
     * Setter allowing to set a new value for source
     * @param city is the city from which the trip starts
     */
    public void setSource(CityModel city){
        Source = city;
    }

    /**
     * Setter allowing to set a new value for date
     * @param date of the beginning of the trip
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Setter allowing to set a new value for nameTrip
     * @param nameTrip is the name of the trip
     */
    public void setName(String nameTrip) {
        this.name = nameTrip;
    }


}
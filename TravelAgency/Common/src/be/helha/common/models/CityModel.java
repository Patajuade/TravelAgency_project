package be.helha.common.models;

import java.io.Serializable;

/**
 * Model for city objects
 *
 */
public class CityModel implements Serializable {
    String cityName;
    String latitude;
    String longitude;
    String countryName;

    /**
     * Default constructor
     */
    public CityModel(){}

    /**
     * constructor
     * @param cityName city name
     * @param latitude city latitude value
     * @param longitude city longitude value
     * @param countryName city country name
     */
    public CityModel(String cityName, String latitude, String longitude, String countryName) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.countryName = countryName;
    }

    /**
     * City name getter, to use its name outside this class
     * @return city name
     */
    //getters
    public String getCityName() {
        return cityName;
    }

    /**
     * City latitude getter, to use its latitude outside this class
     * @return latitude value as a double
     */
    public double getLatitudeDouble(){
        return Double.parseDouble(latitude);
    }

    public double getLongitudeDouble(){
        return Double.parseDouble(longitude);
    }

    /**
     * City country getter, to use its longitude outside this class
     * @return city's countru
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Calculate distance between two points in latitude and longitude
     * Uses Haversine method as its base
     * @param source whole city object taken as the starting point of the calculation
     * @return distance in meters if there is a source, or 0 if there is no source.
     */
    public double distanceCompute(CityModel source) {
        final int R = 6371; // Radius of the earth
        if (source != null){
            double latDistance = Math.toRadians(this.getLatitudeDouble() - source.getLatitudeDouble());
            double lonDistance = Math.toRadians(this.getLongitudeDouble() - source.getLongitudeDouble());
            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(source.getLatitudeDouble())) *
                    Math.cos(Math.toRadians(this.getLatitudeDouble()))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double distanceLocal = R * c * 1000; // convert to meters
            distanceLocal = Math.pow(distanceLocal, 2);
            double distance = Math.sqrt(distanceLocal)/1000;
            //Pour n'afficher que 2 décimales sans prise de tête
            distance = (double) Math.round(distance * 100) / 100;
            return distance;
        }
        return 0;
    }

    /**
     * Overrides original ToString method with a custom one
     * @return formated string for city and country name
     */
    @Override
    public String toString(){
        return this.getCityName()+" (" + this.getCountryName()+")";
    }
}

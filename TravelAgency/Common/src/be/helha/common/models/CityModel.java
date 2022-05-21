package be.helha.common.models;

import java.io.Serializable;

public class CityModel implements Serializable {
    String cityName;
    String latitude;
    String longitude;
    String countryName;

    public CityModel(){}

    public CityModel(String cityName, String latitude, String longitude, String countryName) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.countryName = countryName;
    }

    //getters
    public String getCityName() {
        return cityName;
    }

    public double getLatitudeDouble(){
        return Double.parseDouble(latitude);
    }

    public double getLongitudeDouble(){
        return Double.parseDouble(longitude);
    }

    public String getCountryName() {
        return countryName;
    }

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
    @Override
    public String toString(){
        return this.getCityName()+" (" + this.getCountryName()+")";
    }
}

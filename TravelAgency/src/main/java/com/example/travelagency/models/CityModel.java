package com.example.travelagency.models;

public class CityModel {
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

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
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

    //setters
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString(){
        return this.getCityName()+" (" + this.getCountryName()+")";
    }
}

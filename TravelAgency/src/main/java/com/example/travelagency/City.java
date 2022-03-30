package com.example.travelagency;

public class City {
    String cityName;
    String latitude;
    String longitude;
    String countryName;

    //public City(){}

    public City(String cityName, String latitude, String longitude, String countryName) {
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
}

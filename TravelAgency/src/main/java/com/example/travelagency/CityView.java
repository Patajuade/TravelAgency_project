package com.example.travelagency;

import java.util.ArrayList;

public class CityView {

    public void showCities(ArrayList<CityModel> cities){
        for (CityModel city : cities) {
            System.out.println(city.getCityName()); //cityName
            System.out.println(city.getLatitude()); //latitude
            System.out.println(city.getLongitude()); //longitude
            System.out.println(city.getCountryName()+"\n"); //countryName
        }
    }

    public void showCity(CityModel city){
        System.out.println(city.getCityName());
        System.out.println(city.getLatitude());
        System.out.println(city.getLongitude());
        System.out.println(city.getCountryName());

    }
}

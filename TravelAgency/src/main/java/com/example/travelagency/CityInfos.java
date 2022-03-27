package com.example.travelagency;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityInfos {
    String cityName;
    String latitude;
    String longitude;
    String countryName;
    String[] city = new String[] {cityName,latitude,longitude,countryName};

    public CityInfos(String cityName, String latitude, String longitude, String countryName) {
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

    public void parseMapInfoFile() throws IOException {
        //Sur 11 lignes, je dois ignorer les lignes 2,6,7,8,9,10,11
        // je dois lire que les lignes 1 3 4 5 (donc 0 2 3 4)
        // mettre tout le file dans une liste puis parser la liste en morceaux de 11 lignes

        ArrayList<String> citiesList = new ArrayList<>();
        List<List<String>> partitions = new ArrayList<>();
        int partitionSize = 11;

        BufferedReader br = new BufferedReader(new FileReader("mapinfo.txt"));
        while (br.ready()) {
            citiesList.add(br.readLine());
            }

        for (int i=0; i<citiesList.size(); i += partitionSize) {
            partitions.add(citiesList.subList(i, Math.min(i + partitionSize, citiesList.size())));
        }
        System.out.println(partitions.get(0).get(0)); // = cityName
        System.out.println(partitions.get(0).get(2)); // = latitude
        System.out.println(partitions.get(0).get(3)); // = longitude
        System.out.println(partitions.get(0).get(4)); // = countryName

        //for (List<String> list : partitions) {
          //  System.out.println(partitions.get(0));
        //}

    }
}

package com.example.travelagency;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CityInfos {

    City city;
    private ArrayList<String> infosList = new ArrayList<>();
    private List<List<String>> partitions = new ArrayList<>();
    private ArrayList<City> citiesList = new ArrayList<City>();
    int partitionSize = 11;

    public CityInfos(City city) {
        this.city = city;
    }

    public void parseMapInfoFile() throws IOException {
        //Sur 11 lignes, je dois ignorer les lignes 2,6,7,8,9,10,11
        // je dois lire que les lignes 1 3 4 5 (donc 0 2 3 4)
        // mettre tout le File dans une liste puis parser la liste en morceaux de 11 lignes

        BufferedReader br = new BufferedReader(new FileReader("mapinfo.txt"));
        while (br.ready()) {
            infosList.add(br.readLine());
            }

        for (int i = 0; i< infosList.size(); i += partitionSize) { //partitionne le fichier texte en sous-listes en taille de 11
            partitions.add(infosList.subList(i, Math.min(i + partitionSize, infosList.size())));
            //city = new City(partitions.get(i).get(0),partitions.get(i).get(2),partitions.get(i).get(3),partitions.get(i).get(4));
            //citiesList.add(i,city);

        }

        //affiche une ville en particulier
        //System.out.println(partitions.get(0).get(0)); // = cityName
        //System.out.println(partitions.get(0).get(2)); // = latitude
        //System.out.println(partitions.get(0).get(3)); // = longitude
        //System.out.println(partitions.get(0).get(4)); // = countryName

        //affiche toutes les villes avec les infos triées
        for (List<String> list : partitions) {
            System.out.println(list.get(0)); //cityName
            System.out.println(list.get(2)); //latitude
            System.out.println(list.get(3)); //longitude
            System.out.println(list.get(4)+"\n"); //countryName

        }
        //System.out.println(citiesList.get(0).getCityName());
    }

    public void filterCityInfo(){
        City city = new City("","","","");
        String test;
        //for (List<String> list : partitions) {
        //  System.out.println(partitions.get(0));
        //}
        for (int i = 0; i< infosList.size(); i += partitionSize){
            test = partitions.get(i).toString();
            System.out.println(test);
        }
    }
}

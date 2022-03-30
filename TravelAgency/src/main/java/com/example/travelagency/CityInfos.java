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
            while (br.ready()) { //on met chaque ligne du fichier texte dans une liste
                infosList.add(br.readLine());
            }
            for (int i = 0; i< infosList.size(); i += partitionSize) { //coupe la liste en partitions de 11 (car 11 lignes = une ville)
                partitions.add(infosList.subList(i, Math.min(i + partitionSize, infosList.size())));
            }
        }

        public void initCitiesList(){
            for (List<String> list : partitions) {
                city = new City(list.get(0),list.get(2),list.get(3),list.get(4));
                citiesList.add(city);
            }
        }

        public void showCitiesList(){
            for (List<String> list : partitions) {
                System.out.println(list.get(0)); //cityName
                System.out.println(list.get(2)); //latitude
                System.out.println(list.get(3)); //longitude
                System.out.println(list.get(4)+"\n"); //countryName
            }
        }

        public void showOneCity(int indexPartition){
            System.out.println(partitions.get(indexPartition).get(0)); // = cityName
            System.out.println(partitions.get(indexPartition).get(2)); // = latitude
            System.out.println(partitions.get(indexPartition).get(3)); // = longitude
            System.out.println(partitions.get(indexPartition).get(4)); // = countryName
        }
    }

    package com.example.travelagency;

    import java.io.*;
    import java.util.ArrayList;
    import java.util.List;

    public class CityController {

        private CityModel model;
        private CityView view;
        private ArrayList<CityModel> citiesList = new ArrayList<CityModel>();
        private int partitionSize = 11;

        public CityController(CityModel city) {
            this.model = city;
        }

        public CityController() {
            this.model = new CityModel();
            this.view = new CityView();
        }

        public void init() throws IOException {
            ArrayList<String> infosList = new ArrayList<>();
            List<List<String>> partitions = new ArrayList<>();
            parseMapInfoFile(infosList, partitions);
            initCitiesList(partitions);
        }

        private void parseMapInfoFile(ArrayList<String> infosList, List<List<String>> partitions) throws IOException {
            //Sur 11 lignes, je dois ignorer les lignes 2,6,7,8,9,10,11
            // je dois lire que les lignes 1 3 4 5 (donc 0 2 3 4)
            // mettre tout le File dans une liste puis parser la liste en morceaux de 11 lignes

            BufferedReader br = new BufferedReader(new FileReader("mapinfo.txt"));
            while (br.ready()) { //on met chaque ligne du fichier texte dans une liste
                infosList.add(br.readLine());

            }
            br.close();
            for (int i = 0; i< infosList.size(); i += partitionSize) { //coupe la liste en partitions de 11 (car 11 lignes = une ville)
                partitions.add(infosList.subList(i, Math.min(i + partitionSize, infosList.size())));
            }
        }

        private void initCitiesList(List<List<String>> partitions){
            for (List<String> list : partitions) {
                CityModel city = new CityModel(list.get(0),list.get(2),list.get(3),list.get(4));
                citiesList.add(city);
            }
        }

        public void chooseCity(String s){ //on cherche un objet ville à partir d'un string
            for(CityModel cities : citiesList){
                if (cities.getCityName().equals(s)){
                    model = cities;
                    view.showCity(cities);
                }
            }
        }

        public void showCity(){
            view.showCity(model);
        }

        public void showCities(){
            view.showCities(citiesList);
        }

    }

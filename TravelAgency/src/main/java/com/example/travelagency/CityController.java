    package com.example.travelagency;

    import java.io.*;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Locale;

    public class CityController {

        private CityModel model;
        private CityView view;
        public ArrayList<CityModel> getCitiesList() {
            return citiesList;
        }
        private ArrayList<CityModel> citiesList = new ArrayList<CityModel>();
        private int partitionSize = 11;
        public CityController(CityModel city) {
            this.model = city;
        }
        private static CityController instance = new CityController();

        //Get the only object available
        public static CityController getInstance(){
            return instance;
        }

        //make the constructor private so that this class cannot be instantiated
        private CityController() {
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
            // mettre tout le File dans une liste puis parser la liste en morceaux de 11 lignes
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\thomc\\Documents\\GitHub\\Groupe14\\TravelAgency\\mapInfo.txt"));
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
            for(CityModel city : citiesList){
                if (city.getCityName().equals(s)){
                    model = city;
                    showCity();
                }
            }
        }

        public ArrayList<CityModel> searchCityByName(String s){ // but : à chaque fois qu'on appuie sur une lettre dans la recherche, on appelle cette fct
            ArrayList<CityModel> matches = new ArrayList<CityModel>();
            for(CityModel city : citiesList){
                if (city.getCityName().contains(s) || city.getCountryName().contains(s)){
                    matches.add(city);
                }
                else if(city.getCityName().toLowerCase(Locale.ROOT).contains(s) || city.getCountryName().toLowerCase(Locale.ROOT).contains(s)){
                    matches.add(city);
                }
            }
            return matches;
        }

        public void showCity(){
            view.showCity(model);
        }

        public void showAllCities(){
            view.showCities(citiesList);
        }

        public void showCities(ArrayList<CityModel> list){
            view.showCities(list);
        }

    }

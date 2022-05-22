    package com.example.travelagency.models;

    import java.io.*;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Locale;

    /**
     *Management class for the file with all the informations of the cities
     */
    public class ManagementCity {

        private CityModel model;
        private ArrayList<CityModel> citiesList = new ArrayList<CityModel>();
        private int partitionSize = 11;
        private static ManagementCity instance = null;

        /**
         * Getter allowing to use citiesList outside this class.
         * @return the list of cities
         */
        public ArrayList<CityModel> getCitiesList() {
            return citiesList;
        }

        /**
         * Singleton design pattern for the instanciation of ManagementCity
         * Gets the only object available
         * @return an instance of ManagementCity
         * @throws IOException for file reading
         */
        public static ManagementCity getInstance() throws IOException {
            if(instance==null){
                instance = new ManagementCity();
                instance.init();
            }
            return instance;
        }

        /**
         * Makes the constructor private so that this class cannot be instantiated
         */
        public ManagementCity() {
            this.model = new CityModel();
        }

        /**
         * Initiation of the infos list and the partition list of list, and their parsing
         * @throws IOException for file reading
         */
        private void init() throws IOException {
            ArrayList<String> infosList = new ArrayList<>();
            List<List<String>> partitions = new ArrayList<>();
            parseMapInfoFile(infosList, partitions);
            initCitiesList(partitions);
        }

        /**
         * Reads the file and parses it into 11 lines fragments
         * @param infosList is the list in which we put the whole mapInfo.txt
         * @param partitions is the list in which there are lists of 11 lines
         * @throws IOException for file reading
         */
        private void parseMapInfoFile(ArrayList<String> infosList, List<List<String>> partitions) throws IOException {
            // mettre tout le File dans une liste puis parser la liste en morceaux de 11 lignes
            BufferedReader br = new BufferedReader(new FileReader("mapInfo.txt"));
            while (br.ready()) { //on met chaque ligne du fichier texte dans une liste
                infosList.add(br.readLine());

            }
            br.close();
            for (int i = 0; i< infosList.size(); i += partitionSize) { //coupe la liste en partitions de 11 (car 11 lignes = une ville)
                partitions.add(infosList.subList(i, Math.min(i + partitionSize, infosList.size())));
            }
        }

        /**
         * Selects 4 lines from the partition and makes a City object out of them
         * @param partitions is the list in which there are lists of 11 lines
         */
        private void initCitiesList(List<List<String>> partitions){
            for (List<String> list : partitions) {
                CityModel city = new CityModel(list.get(0),list.get(2),list.get(3),list.get(4));
                citiesList.add(city);
            }
        }

        /**
         * Research the city we want letter by letter
         * @param s is the string we are typing in the search bar
         * @return a city which name matches with what we typed
         */
        public ArrayList<CityModel> searchCityByName(String s){ // but : à chaque fois qu'on appuie sur une lettre dans la recherche, on appelle cette fct
            ArrayList<CityModel> matches = new ArrayList<CityModel>();
            for(CityModel city : citiesList){
                if (city.toString().toLowerCase(Locale.ROOT).contains(s.toLowerCase(Locale.ROOT))){
                    matches.add(city);
                }
            }
            return matches;
        }

    }

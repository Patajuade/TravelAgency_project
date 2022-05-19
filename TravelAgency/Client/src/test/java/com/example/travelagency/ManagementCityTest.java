package com.example.travelagency;
import static org.junit.jupiter.api.Assertions.assertTrue;
import be.helha.common.models.CityModel;
import be.helha.common.models.ManagementCity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


public class ManagementCityTest {

    private ManagementCity managementCity;

    @BeforeEach
    public void setUp() throws IOException {
        managementCity = ManagementCity.getInstance(); //car singleton
    }

    //TODO : Tester les methodes privées ? Si oui comment ?

    @Test
    public void searchCityByName_ifNotInList(){
        ArrayList<CityModel> list = managementCity.searchCityByName("screugneugneu");
        assertTrue(list.size()==0);
    }
    @Test
    public void searchCityByName_ifInList(){
        ArrayList<CityModel> list = managementCity.searchCityByName("Marseille");
        assertTrue(list.size()==1);
    }

}

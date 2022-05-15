package com.example.travelagency;
import com.example.travelagency.models.CityModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CityModelTest {

    @Test
    public void getCityName(){
        CityModel city = new CityModel("test","0","0","test");
        assertEquals(city.getCityName(),"test");
        assertNotEquals(city.getCityName(),"0");
    }
    //TODO : pour distanceCompute
    //tester pour 2 villes identiques (même latitude et même longitude)

    @Test
    public void distanceCompute_WhenCitiesAreTheSamePosition(){
        CityModel city1 = new CityModel("city1","0","0","test");
        CityModel city2 = new CityModel("city2","0","0","test");
        double distance = city1.distanceCompute(city2);
        assertEquals(distance,0);
        assertEquals(city2.distanceCompute(city1),distance);
    }

    @Test
    public void distanceCompute_WhenCitiesArentTheSamePosition(){
        CityModel city1 = new CityModel("city1","100","100","test");
        CityModel city2 = new CityModel("city2","0","0","test");
        double distance = city1.distanceCompute(city2);
        System.out.println(distance);
        assertEquals(distance,9815.41);
        assertEquals(city2.distanceCompute(city1),distance);
    }

}

package com.example.travelagency;
import com.example.travelagency.models.CityModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CityModelTest {

    @Test
    public void getCityName(){
        CityModel city = new CityModel("test","0","0","test");
        assertTrue(city.getCityName().equals("test"));
    }
}

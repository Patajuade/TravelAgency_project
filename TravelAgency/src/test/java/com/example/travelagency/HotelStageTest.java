package com.example.travelagency;
import com.example.travelagency.models.CityModel;
import com.example.travelagency.models.HotelStage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HotelStageTest {

    @Test
    public void priceCompute(){
        HotelStage hotelStage = new HotelStage();
        hotelStage.setNumberOfNights(2);
        hotelStage.setPricePerNight(10);
        int pricePerNight = hotelStage.getPricePerNight();
        int numberOfNights = hotelStage.getNumberOfNights();
        hotelStage.priceCompute();
        assertEquals(20,pricePerNight*numberOfNights);
    }

    @Test
    public void durationCompute(){
        HotelStage hotelStage = new HotelStage();
        hotelStage.setNumberOfNights(2);
        int numberOfNights = hotelStage.getNumberOfNights();
        hotelStage.durationCompute();
        assertEquals(48,numberOfNights*24);
    }
}

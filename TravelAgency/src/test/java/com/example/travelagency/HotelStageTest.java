package com.example.travelagency;
import com.example.travelagency.models.CityModel;
import com.example.travelagency.models.HotelStage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HotelStageTest {

    private HotelStage hotelStage;

    @BeforeEach
    public void setUp(){
        hotelStage = new HotelStage();
        //un nouveau hotelstage est toujours créé avant chaque test pour garantir leur indépendance.
    }

    @Test
    public void priceCompute(){
        hotelStage.setNumberOfNights(2);
        hotelStage.setPricePerNight(10);
        int pricePerNight = hotelStage.getPricePerNight();
        int numberOfNights = hotelStage.getNumberOfNights();
        hotelStage.priceCompute();
        assertEquals(20,pricePerNight*numberOfNights);
    }

    @Test
    public void durationCompute(){
        hotelStage.setNumberOfNights(2);
        int numberOfNights = hotelStage.getNumberOfNights();
        hotelStage.durationCompute();
        assertEquals(48,numberOfNights*24);
    }
}

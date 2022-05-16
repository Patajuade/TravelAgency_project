package com.example.travelagency;
import com.example.travelagency.models.CityModel;
import com.example.travelagency.models.HotelStage;
import com.example.travelagency.models.PlaneStage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlaneStageTest {

    private PlaneStage planeStage;

    @BeforeEach
    public void setUp(){
        planeStage = new PlaneStage();
        //un nouveau PlaneStage est toujours créé avant chaque test pour garantir leur indépendance.
    }

    @Test
    public void priceCompute(){
        planeStage.setDistance(100);
        planeStage.setPricePerKm(10);
        double distance = planeStage.getDistance();
        double pricePerKm = planeStage.getPricePerKm();
        double price = (double) Math.round(distance * pricePerKm * 100) / 100;
        planeStage.priceCompute();
        assertEquals(1000,price);
    }

    @Test
    public void durationCompute(){
        planeStage.setWaitingTime(60);
        planeStage.setDistance(1000);
        planeStage.setFlyingSpeed(700);
        int waitingTime = planeStage.getWaitingTime();
        double distance = planeStage.getDistance();
        int flyingSpeed = planeStage.getSpeed();
        double duration = (waitingTime + (distance * 60/flyingSpeed)) /60;
        duration = (double) Math.round(duration * 100) / 100;
        planeStage.durationCompute();
        assertEquals(2.43,duration);
    }
    //TODO : ajouter un test pour updateData ?
}

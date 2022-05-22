package be.helha.travelagency;
import be.helha.common.models.PlaneStage;
import org.junit.jupiter.api.AfterEach;
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
        //arrange
        planeStage.setDistance(100);
        planeStage.setPricePerKm(10);
        //act
        planeStage.priceCompute();
        //assert
        assertEquals(1000,planeStage.getPrice());
    }

    @Test
    public void durationCompute(){
        //arrange
        planeStage.setWaitingTime(60);
        planeStage.setDistance(1000);
        planeStage.setFlyingSpeed(700);
        //act
        planeStage.durationCompute();
        //assert
        assertEquals(2.43,planeStage.getDuration());
    }

    @AfterEach
    public void undefPlaneStage(){
        planeStage = null;
    }


    //TODO : ajouter un test pour updateData ?
}

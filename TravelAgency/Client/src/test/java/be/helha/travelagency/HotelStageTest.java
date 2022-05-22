package be.helha.travelagency;
import be.helha.common.models.HotelStage;
import org.junit.jupiter.api.AfterEach;
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
        //arrange
        hotelStage.setNumberOfNights(2);
        hotelStage.setPricePerNight(10);
        //act
        hotelStage.priceCompute();
        //assert
        assertEquals(20,hotelStage.getPricePerNight()*hotelStage.getNumberOfNights());
    }

    @Test
    public void durationCompute(){
        //arrange
        hotelStage.setNumberOfNights(2);
        //act
        hotelStage.durationCompute();
        //assert
        assertEquals(48,hotelStage.getNumberOfNights()*24);
    }

    @AfterEach
    public void undefHotelStage(){
        hotelStage = null;
    }
}

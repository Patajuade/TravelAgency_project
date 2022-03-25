module com.example.travelagencyproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens TravelAgencyProject to javafx.fxml;
    opens TravelAgencyProject.Models to javafx.fxml;
    opens TravelAgencyProject.Controllers to javafx.fxml;
}
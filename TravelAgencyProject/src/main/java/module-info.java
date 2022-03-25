module com.example.travelagencyproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.travelagencyproject to javafx.fxml;
    exports com.example.travelagencyproject;
}
module com.example.travelagency {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.travelagency to javafx.fxml;
    exports com.example.travelagency;
}
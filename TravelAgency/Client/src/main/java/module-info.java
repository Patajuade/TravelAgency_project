module com.example.travelagency {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    requires Common;
    opens com.example.travelagency.views to javafx.fxml;
    exports com.example.travelagency.controllers;
    opens com.example.travelagency.controllers to javafx.fxml;
}
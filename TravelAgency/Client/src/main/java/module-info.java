module Client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    requires Common;
    opens be.helha.travelagency.views to javafx.fxml;
    exports be.helha.travelagency.controllers;
    opens be.helha.travelagency.controllers to javafx.fxml;
}
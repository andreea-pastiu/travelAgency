package org.travelagency.controller;

import java.io.IOException;
import javafx.fxml.FXML;

public class AdminController {

    @FXML
    private void addDestination() throws IOException {
        App.setRoot("addDestination");
    }

    @FXML
    private void viewDestinations() throws IOException {
        App.setRoot("viewDestinations");
    }

    @FXML
    private void viewPackages() throws IOException {
        App.setRoot("viewPackagesAdmin");
    }
}

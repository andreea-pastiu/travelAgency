package org.travelagency.controller;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToAdmin() throws IOException {
        App.setRoot("admin");
    }

    @FXML
    private void switchToUser() throws IOException {
        App.setRoot("user");
    }
}

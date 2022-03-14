package org.travelagency.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.travelagency.model.Destination;
import org.travelagency.service.DestinationService;

import java.io.IOException;

public class AddDestinationController {
    private final DestinationService destinationService = new DestinationService();
    @FXML
    TextField nameTextField;

    @FXML
    private void goToHome() throws IOException
    {
        App.setRoot("admin");
    }

    @FXML
    private void addDestination()
    {
        String name = nameTextField.getText();
        Destination destination = new Destination(0, name);
        try
        {
            destinationService.addDestination(destination);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Destination Added");
            alert.show();
        } catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
}

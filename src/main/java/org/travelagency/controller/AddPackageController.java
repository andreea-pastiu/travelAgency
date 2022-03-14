package org.travelagency.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.travelagency.model.Destination;
import org.travelagency.model.Package;
import org.travelagency.service.PackageService;
import org.w3c.dom.Text;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddPackageController
{
    private static Destination destination;
    private PackageService packageService = new PackageService();

    public static void setDestination(Destination destination)
    {
        AddPackageController.destination = destination;
    }
    @FXML
    TextField nameTextField;
    @FXML
    TextField priceTextField;
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker endDatePicker;
    @FXML
    TextField extraDetailsTextField;
    @FXML
    TextField maxPeopleTextField;

    @FXML
    private void goToHome() throws IOException
    {
        App.setRoot("admin");
    }

    @FXML
    private void addPackage()
    {
        String name = nameTextField.getText();
        BigDecimal price = new BigDecimal(priceTextField.getText());
        LocalDate startDateLocal = startDatePicker.getValue();
        Date startDate = Date.from(startDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate endDateLocal = endDatePicker.getValue();
        Date endDate = Date.from(endDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String extraDetails = extraDetailsTextField.getText();
        int maxPeople = Integer.parseInt(maxPeopleTextField.getText());
        Package packageObj = new Package(0, name, price, startDate, endDate, extraDetails, maxPeople, 0, destination);
        try
        {
            packageService.addPackage(packageObj);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Package Added");
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

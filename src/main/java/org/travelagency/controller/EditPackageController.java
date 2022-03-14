package org.travelagency.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.travelagency.model.Package;
import org.travelagency.service.PackageService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EditPackageController
{
    private PackageService packageService = new PackageService();
    private static Package packageObj;

    public static void setPackage(Package packageObj)
    {
        EditPackageController.packageObj = packageObj;
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
    public void initialize()
    {
        nameTextField.setText(packageObj.getName());
        priceTextField.setText(String.valueOf(packageObj.getPrice()));
        startDatePicker.setValue(packageObj.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        endDatePicker.setValue(packageObj.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        extraDetailsTextField.setText(packageObj.getExtraDetails());
        maxPeopleTextField.setText(String.valueOf(packageObj.getMaxPeople()));
    }

    @FXML
    private void editPackage()
    {
        String name = nameTextField.getText();
        BigDecimal price = new BigDecimal(priceTextField.getText());
        LocalDate startDateLocal = startDatePicker.getValue();
        Date startDate = Date.from(startDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate endDateLocal = endDatePicker.getValue();
        Date endDate = Date.from(endDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String extraDetails = extraDetailsTextField.getText();
        int maxPeople = Integer.parseInt(maxPeopleTextField.getText());
        Package packageObjNew = new Package(packageObj.getId(), name, price, startDate, endDate, extraDetails, maxPeople, 0, packageObj.getDestination());
        try
        {
            packageService.editPackage(packageObjNew);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Package modified");
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

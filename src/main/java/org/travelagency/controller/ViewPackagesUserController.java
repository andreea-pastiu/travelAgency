package org.travelagency.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.travelagency.model.Package;
import org.travelagency.service.PackageService;
import org.travelagency.service.UserService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ViewPackagesUserController
{
    private final PackageService packageService = new PackageService();
    private final UserService userService = new UserService();

    @FXML
    TableView packagesTable;
    @FXML
    TextField destinationTextField;
    @FXML
    TextField priceTextField;
    @FXML
    DatePicker startDatePicker;
    @FXML
    DatePicker endDatePicker;

    @FXML
    public void initialize()
    {
        packagesTable.getItems().removeAll(packagesTable.getItems());
        packagesTable.getColumns().removeAll(packagesTable.getColumns());
        List<Package> packages = packageService.getAllValidPackages();
        TableColumn<Package, String> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Package, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Package, String> column3 = new TableColumn<>("Price");
        column3.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Package, String> column4 = new TableColumn<>("Start Date");
        column4.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        TableColumn<Package, String> column5 = new TableColumn<>("End Date");
        column5.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        TableColumn<Package, String> column6 = new TableColumn<>("Extra Details");
        column6.setCellValueFactory(new PropertyValueFactory<>("extraDetails"));
        TableColumn<Package, String> column7 = new TableColumn<>("Current People");
        column7.setCellValueFactory(new PropertyValueFactory<>("currentPeople"));
        TableColumn<Package, String> column8 = new TableColumn<>("Max People");
        column8.setCellValueFactory(new PropertyValueFactory<>("maxPeople"));
        TableColumn<Package, String> column9 = new TableColumn<>("Destination");
        column9.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination().getName()));
        TableColumn<Package, Package> bookVacation = new TableColumn<>("");
        bookVacation.setMinWidth(40);
        bookVacation.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        bookVacation.setCellFactory(param -> new TableCell<Package, Package>()
        {
            private final Button bookVacationButton = new Button("Book Vacation");

            @Override
            protected void updateItem(Package packageObj, boolean empty)
            {
                super.updateItem(packageObj, empty);

                if (packageObj == null)
                {
                    setGraphic(null);
                    return;
                }

                setGraphic(bookVacationButton);
                bookVacationButton.setOnAction(event ->
                {
                    userService.addVacationToUser(App.getLoggedUser(), packageObj);
                    packageObj.incrementCurrentPeople();
                    try
                    {
                        packageService.editPackage(packageObj);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Vacation booked");
                    alert.show();
                });
            }
        });
        packagesTable.getColumns().add(column1);
        packagesTable.getColumns().add(column2);
        packagesTable.getColumns().add(column3);
        packagesTable.getColumns().add(column4);
        packagesTable.getColumns().add(column5);
        packagesTable.getColumns().add(column6);
        packagesTable.getColumns().add(column7);
        packagesTable.getColumns().add(column8);
        packagesTable.getColumns().add(column9);
        packagesTable.getColumns().add(bookVacation);
        packagesTable.getItems().addAll(packages);
    }

    @FXML
    public void filter()
    {
        String destination = destinationTextField.getText();
        BigDecimal price = new BigDecimal(priceTextField.getText());
        LocalDate startDateLocal = startDatePicker.getValue();
        Date startDate = Date.from(startDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate endDateLocal = endDatePicker.getValue();
        Date endDate = Date.from(endDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Package> packages = null;
        try
        {
            packages = packageService.filterPackages(startDate, endDate, price, destination);
        } catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.show();
            return;
        }
        packagesTable.getItems().removeAll(packagesTable.getItems());
        packagesTable.getItems().addAll(packages);
    }

    @FXML
    private void viewVacations() throws IOException {
        App.setRoot("viewVacations");
    }
}

package org.travelagency.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.travelagency.model.Package;
import org.travelagency.service.PackageService;

import java.io.IOException;
import java.util.List;

public class ViewPackagesAdminController
{
    private final PackageService packageService = new PackageService();

    @FXML
    TableView packagesTable;

    @FXML
    private void goToHome() throws IOException
    {
        App.setRoot("admin");
    }

    @FXML
    public void initialize()
    {
        packagesTable.getItems().removeAll(packagesTable.getItems());
        packagesTable.getColumns().removeAll(packagesTable.getColumns());
        List<Package> packages = packageService.getAllPackages();
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
        TableColumn<Package, String> column9 = new TableColumn<>("Status");
        column9.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        TableColumn<Package, Package> editPackage = new TableColumn<>("");
        editPackage.setMinWidth(40);
        editPackage.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        editPackage.setCellFactory(param -> new TableCell<Package, Package>()
        {
            private final Button editPackageButton = new Button("Edit package");

            @Override
            protected void updateItem(Package packageObj, boolean empty)
            {
                super.updateItem(packageObj, empty);

                if (packageObj == null)
                {
                    setGraphic(null);
                    return;
                }

                setGraphic(editPackageButton);
                editPackageButton.setOnAction(event ->
                {
                    try
                    {
                        EditPackageController.setPackage(packageObj);
                        App.setRoot("editPackage");
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });
            }
        });
        TableColumn<Package, Package> deletePackage = new TableColumn<>("");
        deletePackage.setMinWidth(40);
        deletePackage.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deletePackage.setCellFactory(param -> new TableCell<Package, Package>()
        {
            private final Button deletePackageButton = new Button("Delete package");

            @Override
            protected void updateItem(Package packageObj, boolean empty)
            {
                super.updateItem(packageObj, empty);

                if (packageObj == null)
                {
                    setGraphic(null);
                    return;
                }

                setGraphic(deletePackageButton);
                deletePackageButton.setOnAction(event ->
                {
                    try
                    {
                        packageService.deletePackage(packageObj);
                        packagesTable.getItems().remove(packageObj);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setContentText("Package deleted");
                        alert.show();
                    }
                    catch(Exception e)
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Cannot delete package with bookings");
                        alert.show();
                    }
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
        packagesTable.getColumns().add(editPackage);
        packagesTable.getColumns().add(deletePackage);
        packagesTable.getItems().addAll(packages);
    }
}

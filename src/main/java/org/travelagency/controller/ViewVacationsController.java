package org.travelagency.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.travelagency.model.Package;
import org.travelagency.service.PackageService;
import org.travelagency.service.UserService;

import java.io.IOException;
import java.util.List;

public class ViewVacationsController
{
    private final UserService userService = new UserService();
    @FXML
    TableView vacationsTable;

    @FXML
    private void goToHome() throws IOException
    {
        App.setRoot("user");
    }

    @FXML
    public void initialize()
    {
        vacationsTable.getItems().removeAll(vacationsTable.getItems());
        vacationsTable.getColumns().removeAll(vacationsTable.getColumns());
        List<Package> packages = userService.getVacations(App.getLoggedUser());
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
        vacationsTable.getColumns().add(column2);
        vacationsTable.getColumns().add(column3);
        vacationsTable.getColumns().add(column4);
        vacationsTable.getColumns().add(column5);
        vacationsTable.getColumns().add(column6);
        vacationsTable.getItems().addAll(packages);
    }
}

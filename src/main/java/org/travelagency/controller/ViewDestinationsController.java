package org.travelagency.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.travelagency.model.Destination;
import org.travelagency.service.DestinationService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ViewDestinationsController
{
    private final DestinationService destinationService = new DestinationService();

    @FXML
    TableView destinationsTable;


    @FXML
    private void goToHome() throws IOException
    {
        App.setRoot("admin");
    }

    @FXML
    public void initialize()
    {
        destinationsTable.getItems().removeAll(destinationsTable.getItems());
        destinationsTable.getColumns().removeAll(destinationsTable.getColumns());
        List<Destination> destinations = destinationService.getDestinations();
        TableColumn<Destination, String> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Destination, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Destination, Destination> addPackage = new TableColumn<>("");
        addPackage.setMinWidth(40);
        addPackage.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        addPackage.setCellFactory(param -> new TableCell<Destination, Destination>()
        {
            private final Button addPackageButton = new Button("Add package");

            @Override
            protected void updateItem(Destination destination, boolean empty)
            {
                super.updateItem(destination, empty);

                if (destination == null)
                {
                    setGraphic(null);
                    return;
                }

                setGraphic(addPackageButton);
                addPackageButton.setOnAction(event ->
                {
                    try
                    {
                        AddPackageController.setDestination(destination);
                        App.setRoot("addPackage");
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });
            }
        });
        TableColumn<Destination, Destination> deleteDestination = new TableColumn<>("");
        deleteDestination.setMinWidth(40);
        deleteDestination.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteDestination.setCellFactory(param -> new TableCell<Destination, Destination>()
        {
            private final Button deleteDestinationButton = new Button("Delete Destination");

            @Override
            protected void updateItem(Destination destination, boolean empty)
            {
                super.updateItem(destination, empty);

                if (destination == null)
                {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteDestinationButton);
                deleteDestinationButton.setOnAction(event ->
                {
                    destinationService.deleteDestination(destination);
                    destinationsTable.getItems().remove(destination);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Destination deleted");
                    alert.show();
                });
            }
        });
        destinationsTable.getColumns().add(column1);
        destinationsTable.getColumns().add(column2);
        destinationsTable.getColumns().add(addPackage);
        destinationsTable.getColumns().add(deleteDestination);
        destinationsTable.getItems().addAll(destinations);
    }
}

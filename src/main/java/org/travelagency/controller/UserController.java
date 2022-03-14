package org.travelagency.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.travelagency.model.User;
import org.travelagency.service.UserService;

import java.io.IOException;

public class UserController
{
    private UserService userService = new UserService();
    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    private void register() throws IOException
    {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        User user = new User(username, password);
        User userAdded = userService.register(user);
        if(userAdded == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Username already exists");
            alert.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("User created");
            alert.show();
        }
    }

    @FXML
    private void login() throws IOException
    {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        User user = new User(username, password);
        User existingUser = userService.login(user);
        if(existingUser != null)
        {
            App.setLoggedUser(existingUser);
            App.setRoot("viewPackagesUser");
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid username or password");
            alert.show();
        }
    }
}

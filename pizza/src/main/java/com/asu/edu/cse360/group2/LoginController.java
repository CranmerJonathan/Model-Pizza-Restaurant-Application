/**
 * Provides functionality for all Login screen UI elements
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2;

// general imports
import java.io.IOException;
import javafx.fxml.FXML;

// javafx objects
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    private void authenticateLogin() throws IOException {
        if (username.getText().compareTo("chef") == 0 && password.getText().compareTo("password123") == 0) {
            App.setRoot("chef");
        } else if (username.getText().compareTo("delivery") == 0 && password.getText().compareTo("password123") == 0) {
            App.setRoot("delivery");
        } else if (username.getText().compareTo("admin") == 0 && password.getText().compareTo("password123") == 0) {
            App.setRoot("admin");
        } else {
            Alert alert = new Alert(AlertType.ERROR, "Incorrect username/password!");
            alert.show();
        }
    }

    @FXML
    private void switchToCustomerWindow() throws IOException {
        App.setRoot("customerhome");
    }
}

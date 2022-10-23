/**
 * Provides functionality for all Customer screen UI elements
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2.Customer;

// application and application state variables
import com.asu.edu.cse360.group2.App;
import com.asu.edu.cse360.group2.AppState;

// general imports
import java.io.IOException;
import javafx.fxml.FXML;

// javafx objects
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerController {
    @FXML
    private TextField asuID;

    @FXML
    private void makeOrders() throws IOException {
        App.setRoot("customerorders");
    }

    @FXML
    private void viewOrders() throws IOException {
        boolean success = true;
        if (asuID.getText().length() != 10) {
            success = false;
        }
        int ID = 0;
        try {
            ID = Integer.parseInt(asuID.getText());
        } catch (NumberFormatException nfe) {
            success = false;
        }

        if (!success) {
            Alert alert = new Alert(AlertType.ERROR, "Invalid ASU ID");
            alert.show();
            return;
        }

        AppState.CustomerState.currentUserID = ID;
        App.setRoot("customeroverview");
    }

    @FXML
    private void employeeSignin() throws IOException {
        App.setRoot("login");
    }
}

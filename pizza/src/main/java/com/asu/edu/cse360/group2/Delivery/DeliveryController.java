/**
 * Provides functionality for all Delivery employee screen UI elements
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2.Delivery;

// application
import com.asu.edu.cse360.group2.App;

// general imports
import java.io.IOException;
import javafx.fxml.FXML;

// javafx objects
import javafx.scene.control.TableView;

public class DeliveryController {
    @FXML
    private TableView orders;

    @FXML
    private void logout() throws IOException {
        App.setRoot("login");
    }
}

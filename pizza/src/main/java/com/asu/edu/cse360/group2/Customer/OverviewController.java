/**
 * Provides functionality for all Payment screen UI elements
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2.Customer;

// application
import com.asu.edu.cse360.group2.App;
import com.asu.edu.cse360.group2.AppState;
import com.asu.edu.cse360.group2.Order;
import com.asu.edu.cse360.group2.Pizza;

// general imports
import java.io.IOException;
import javafx.fxml.FXML;

// javafx objects
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.control.ProgressBar;

public class OverviewController {
    @FXML
    private TableView<Order> orders;

    @FXML
    private TableColumn<Order, String> ordersColumn;

    @FXML
    private Text stage;
    @FXML
    private Text state;
    @FXML
    private Text estArrival;

    @FXML
    private ProgressBar progress;

    @FXML
    private void initialize() {
        ordersColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));
        if (AppState.orders.get(AppState.CustomerState.currentUserID) != null) {
            orders.setItems(FXCollections.observableList(AppState.orders.get(AppState.CustomerState.currentUserID)));
        } else {
            orders.getItems().clear();
        }

        orders.getSelectionModel().selectedItemProperty().addListener((observableList, oldSelection, newSelection) -> {
            stage.setText("Order " + newSelection.getOrderNumber());
            state.setText(newSelection.getState());
            estArrival.setText("Estimated arrival: " + newSelection.getTime());
            progress.setProgress(newSelection.getProgress());
        });
    }

    @FXML
    private void exit() throws IOException {
        App.setRoot("customerhome");
    }
}

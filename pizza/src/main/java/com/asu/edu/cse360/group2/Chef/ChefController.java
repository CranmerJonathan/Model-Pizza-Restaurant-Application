/**
 * Provides functionality for all Chef employee screen UI elements
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2.Chef;

// application
import com.asu.edu.cse360.group2.App;
import com.asu.edu.cse360.group2.AppState;
import com.asu.edu.cse360.group2.Order;

// general imports
import java.io.IOException;
import javafx.fxml.FXML;
import java.util.ArrayList;
import java.util.Set;

// javafx objects
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ChefController {
    private Order selectedOrder;

    @FXML
    private TableView<Order> orders;

    @FXML
    private TableColumn<Order, String> ordersColumn;

    @FXML
    public void initialize() {
        // this function is needed to load elements into the table when the view loads
        ordersColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));

        // copies every valid order in new orders to array list orderList
        ArrayList<Order> orderList = new ArrayList<Order>();
        Set<Integer> loggedIDs = AppState.approvedOrders.keySet();
        for (Integer ID : loggedIDs) {
            orderList.addAll(AppState.approvedOrders.get(ID));
        }

        orders.setItems(FXCollections.observableList(orderList));

        orders.getSelectionModel().selectedItemProperty().addListener((observableList, oldSelection, newSelection) -> {
            selectedOrder = newSelection;
        });

    }

    @FXML
    private void bakedStage() {
        int ID = selectedOrder.getUserID();
        selectedOrder.setState(3);
        if (AppState.bakedOrders.get(ID) == null) {
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(selectedOrder);
            AppState.bakedOrders.put(ID, orders);
        } else {
            ArrayList<Order> orders = AppState.bakedOrders.get(ID);
            orders.add(selectedOrder);
        }
    }

    @FXML
    private void doneStage() {
        int ID = selectedOrder.getUserID();
        selectedOrder.setState(4);
        if (AppState.doneOrders.get(ID) == null) {
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(selectedOrder);
            AppState.doneOrders.put(ID, orders);
        } else {
            ArrayList<Order> orders = AppState.doneOrders.get(ID);
            orders.add(selectedOrder);
        }

        ArrayList<Order> newOrderForIDList = AppState.doneOrders.get(selectedOrder.getUserID());
        newOrderForIDList.remove(selectedOrder);
        orders.getItems().remove(selectedOrder); // updates table
    }

    @FXML
    private void logout() throws IOException {
        App.setRoot("login");
    }
}

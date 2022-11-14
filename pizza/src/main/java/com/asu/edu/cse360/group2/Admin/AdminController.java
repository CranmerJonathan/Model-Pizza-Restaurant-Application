/**
 * Provides functionality for all Admin UI elements
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2.Admin;

// application
import com.asu.edu.cse360.group2.App;
import com.asu.edu.cse360.group2.AppState;
import com.asu.edu.cse360.group2.Order;
import com.asu.edu.cse360.group2.OrderSort;

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

public class AdminController {
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
        Set<Integer> loggedIDs = AppState.newOrders.keySet();
        for (Integer ID : loggedIDs) {
            orderList.addAll(AppState.newOrders.get(ID));
        }

        orders.setItems(FXCollections.observableList(orderList));

        orders.getSelectionModel().selectedItemProperty().addListener((observableList, oldSelection, newSelection) -> {
            selectedOrder = newSelection;
        });
    }

    @FXML
    private void logout() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void approve() {
        if (selectedOrder == null) {
            return;
        }

        // add item to approved list, set order state to approved
        int ID = selectedOrder.getUserID();
        selectedOrder.setState(1);
        if (AppState.approvedOrders.get(ID) == null) {
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(selectedOrder);
            AppState.approvedOrders.put(ID, orders);
        } else {
            ArrayList<Order> orders = AppState.approvedOrders.get(ID);
            orders.add(selectedOrder);
        }

        ArrayList<Order> newOrderForIDList = AppState.newOrders.get(selectedOrder.getUserID());
        newOrderForIDList.remove(selectedOrder);
        orders.getItems().remove(selectedOrder); // updates table
    }

    @FXML
    private void disapprove() {
        if (selectedOrder == null) {
            return;
        }

        // update order state to disapproved
        selectedOrder.setState(2);

        ArrayList<Order> newOrderForIDList = AppState.newOrders.get(selectedOrder.getUserID());
        newOrderForIDList.remove(selectedOrder);
        orders.getItems().remove(selectedOrder); // updates table
    }

    @FXML
    private void sortByTime() {
        OrderSort sorter = new OrderSort(new ArrayList<Order>(orders.getItems()));
        sorter.sortOrdersTime();
        orders.setItems(FXCollections.observableList(sorter.getOrders()));
    }

    @FXML
    private void sortBySize() {
        OrderSort sorter = new OrderSort(new ArrayList<Order>(orders.getItems()));
        sorter.sortOrdersSize();
        orders.setItems(FXCollections.observableList(sorter.getOrders()));
    }
}

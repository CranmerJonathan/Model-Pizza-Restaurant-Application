/**
 * Provides functionality for all Delivery employee screen UI elements
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2.Delivery;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;

public class DeliveryController {
    private Order selectedOrder = null;

    @FXML
    private TableView<Order> orders;

    @FXML
    private TableColumn<Order, String> ordersColumn;

    @FXML
    private Text address;

    @FXML
    private void initialize() {
        // this function is needed to load elements into the table when the view loads
        ordersColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));

        // copies every valid order in new orders to array list orderList
        ArrayList<Order> orderList = new ArrayList<Order>();
        Set<Integer> loggedIDs = AppState.doneOrders.keySet();
        for (Integer ID : loggedIDs) {
            orderList.addAll(AppState.doneOrders.get(ID));
        }

        orders.setItems(FXCollections.observableList(orderList));

        orders.getSelectionModel().selectedItemProperty().addListener((observableList, oldSelection, newSelection) -> {
            selectedOrder = newSelection;
            if (selectedOrder != null) {
                address.setText("Address: " + selectedOrder.getUserAddress());
            }
        });
    }

    @FXML
    private void setDelivering() {
        if (selectedOrder == null) {
            return;
        }
        selectedOrder.setState(5);
        orders.refresh();
    }

    @FXML
    private void setDelivered() {
        if (selectedOrder == null) {
            return;
        }

        int ID = selectedOrder.getUserID();
        selectedOrder.setState(7);

        // we need to remove from done orders app state and add to completed orders
        // app state
        AppState.doneOrders.get(ID).remove(selectedOrder);
        if (AppState.completeOrders.get(ID) == null) {
            ArrayList<Order> orders = new ArrayList<Order>();
            orders.add(selectedOrder);
            AppState.completeOrders.put(ID, orders);
        } else {
            AppState.completeOrders.get(ID).add(selectedOrder);
        }

        orders.getItems().remove(selectedOrder);
        address.setText("Address: ");
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

    @FXML
    private void logout() throws IOException {
        App.setRoot("login");
    }
}

/**
 * Maintains the state of the application
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2;

// application imports
import com.asu.edu.cse360.group2.Pizza;

// general imports
import java.util.ArrayList;
import java.util.Hashtable;
import java.net.URL;
import java.util.Set;

// javafx
import javafx.collections.ObservableList;

public class AppState {
    // global state variables
    // hash table stores customer ASU ID, order list pairs

    // this stores all orders for each customer
    public static Hashtable<Integer, ArrayList<Order>> orders = new Hashtable<Integer, ArrayList<Order>>();

    // this stores all newly placed unapproved orders for each customer
    public static Hashtable<Integer, ArrayList<Order>> newOrders = new Hashtable<Integer, ArrayList<Order>>();

    // this stores all approved orders for each customer (managed by the chef)
    public static Hashtable<Integer, ArrayList<Order>> approvedOrders = new Hashtable<Integer, ArrayList<Order>>();

    // this stores all orders ready for delivery (managed by the delivery driver)
    public static Hashtable<Integer, ArrayList<Order>> deliverableOrders = new Hashtable<Integer, ArrayList<Order>>();

    // order number
    public static int orderNumber = 0;

    // customer state
    static public class CustomerState {
        public static int currentUserID;
        public static ObservableList<Pizza> pizzaList;
        public static String currentAddress;
    }

    // chef state
    static public class ChefState {
        public static int currentOrderId;
        public static ObservableList<Order> orderList;
    }

    // delivery driver state
    static public class DeliveryState {

    }

    // TODO
    // serializes static state into JSON format returned as a string
    public static String serializeToJSON() {
        return null;
    }

    // TODO
    // accepts a file path (URL) and deserializes it and defines static state
    public static void deserializeFromJSON(URL url) {
        return;
    }
}

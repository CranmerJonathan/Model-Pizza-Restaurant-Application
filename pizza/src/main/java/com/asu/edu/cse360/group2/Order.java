/**
 * Stores all relevant parts of an order
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2;

// application state
import com.asu.edu.cse360.group2.AppState;

// general imports
import java.util.List;
import java.net.URL;

public class Order {
    private List<Pizza> pizzas;
    private int orderNumber;
    private double progress;
    private int userID;

    // TODO
    // define int-state mapping table
    private int state = 0;

    public Order(List<Pizza> pizzas, int userID) {
        AppState.orderNumber += 1;
        orderNumber = AppState.orderNumber;
        this.pizzas = pizzas;
        this.progress = 0;
        this.userID = userID;
    }

    // utility functions for table view
    public String getName() {
        return "Order " + orderNumber;
    }

    // gets progress of order (managed by chef/delivery classes)
    public double getProgress() {
        return progress;
    }

    // gets user ID associated with order
    public int getUserID() {
        return userID;
    }

    // gets state of order
    // TODO
    // finish state table
    // consider using state enum
    public String getState() {
        switch (state) {
            case 0:
                return "Processing Order";
            case 1:
                return "Approved";
            case 2:
                return "Disapproved";
            case 3:
                return "Baking";
            case 4:
                return "Done";
            default:
                return "Delivered"; 
        }
    }

    // sets state of order
    public void setState(int state) {
        this.state = state;
    }

    // get estimated time for completion
    // TODO
    // update this correctly
    public String getTime() {
        switch (state) {
            case 0:
                return "30 min.";
            case 1:
                return "15 min.";
            default:
                return "0 min.";
        }
    }

    // TODO
    // accepts a order object and serializes it in JSON format returned as a string
    public static String serializeToJSON(Pizza pizza) {
        return null;
    }

    // TODO
    // accepts a file path (URL) and deserializes it and returns an Order object
    public static Order deserializeFromJSON(URL url) {
        return null;
    }
}
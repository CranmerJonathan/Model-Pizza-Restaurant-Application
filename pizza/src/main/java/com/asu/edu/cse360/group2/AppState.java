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
import java.io.*;
import java.net.URL;
import java.util.Set;

// javafx
import javafx.collections.ObservableList;

public class AppState implements Serializable {
    // global state variables
    // hash table stores customer ASU ID, order list pairs

    // this stores all orders for each customer
    public static Hashtable<Integer, ArrayList<Order>> orders = new Hashtable<Integer, ArrayList<Order>>();

    // this stores all newly placed unapproved orders for each customer
    // admin viewable orders
    public static Hashtable<Integer, ArrayList<Order>> newOrders = new Hashtable<Integer, ArrayList<Order>>();

    // this stores all approved orders for each customer (managed by the chef)
    // chef viewable orders
    public static Hashtable<Integer, ArrayList<Order>> approvedOrders = new Hashtable<Integer, ArrayList<Order>>();

    // this stores all orders ready for delivery (managed by the delivery driver)
    // deprecated in place of doneOrders (see below)
    // public static Hashtable<Integer, ArrayList<Order>> deliverableOrders = new
    // Hashtable<Integer, ArrayList<Order>>();

    // this stores all orders that are being baked (managed by the chef)
    // deprecated in place of single state updates
    // public static Hashtable<Integer, ArrayList<Order>> bakedOrders = new
    // Hashtable<Integer, ArrayList<Order>>();

    // this stores all orders that are done being baked (managed by delivery driver)
    // delivery viewable orders
    public static Hashtable<Integer, ArrayList<Order>> doneOrders = new Hashtable<Integer, ArrayList<Order>>();

    // this stores all completed orders (managed by chef)
    public static Hashtable<Integer, ArrayList<Order>> completeOrders = new Hashtable<Integer, ArrayList<Order>>();

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

    public static void writeAppstateToFile(AppState A, File file) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(A);
            oos.flush();
        }
        
    }

    public static AppState readAppstateFromFile(File file) throws IOException, ClassNotFoundException{
        AppState a = null;
        try(FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis)){
            a = (AppState) ois.readObject();
        }
        return a;
    }
}

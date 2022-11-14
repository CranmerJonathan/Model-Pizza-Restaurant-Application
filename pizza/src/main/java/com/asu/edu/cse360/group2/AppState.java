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
    try{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("order.dat"));
        output.writeObject(AppState.orders);
        output.close();
    }
    catch(IOException ioe){
        System.err.println("Error saving to file");
    }

    // this stores all newly placed unapproved orders for each customer
    public static Hashtable<Integer, ArrayList<Order>> newOrders = new Hashtable<Integer, ArrayList<Order>>();
    
    try{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("newOrders.dat"));
        output.writeObject(AppState.newOrders);
        output.close();
    }
    catch(IOException ioeTwo){
        System.err.println("Error saving to file");
    }

    // this stores all approved orders for each customer (managed by the chef)
    public static Hashtable<Integer, ArrayList<Order>> approvedOrders = new Hashtable<Integer, ArrayList<Order>>();
    try{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("approved.dat"));
        output.writeObject(AppState.approvedOrders);
        output.close();
    }
    catch(IOException ioeThree){
        System.err.println("Error saving to file");
    }
    // this stores all orders ready for delivery (managed by the delivery driver)
    public static Hashtable<Integer, ArrayList<Order>> deliverableOrders = new Hashtable<Integer, ArrayList<Order>>();
    try{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("deliverable.dat"));
        output.writeObject(AppState.deliverableOrders);
        output.close();
    }
    catch(IOException ioeFour){
        System.err.println("Error saving to file");
    }

    // order number
    public static int orderNumber = 0;

    // customer state
    static public class CustomerState {
        public static int currentUserID;
        public static ObservableList<Pizza> pizzaList;
    }

    // chef state
    static public class ChefState {

    }

    // delivery driver state
    static public class DeliveryState {

    }

    // TODO
    // serializes static state into JSON format returned as a string
    public static void serialize(String fileName, Order order) {
        try{
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
            output.writeObject(order);
            output.close();
        }
        catch(IOException ioe){
            System.err.println("Error saving to file");
        }
        
    }

    
    // accepts a file path (URL) and deserializes it and returns an Order object
    public static void deserialize(String fileName, Order order) {
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("pizza.dat"));
            order = (Order) input.readObject();
            input.close();
        }
        catch(IOException ioe){
            System.err.println("Error opening to file");
        }
        catch(ClassNotFoundException cnfe){
            System.err.println("Object read is not of the specified object that we're attempting to save to");
        }
        
    }
}

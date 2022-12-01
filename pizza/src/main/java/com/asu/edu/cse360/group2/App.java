/**
 * Main execution of the Pizza application. 
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.google.gson.*;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

//import com.asu.edu.cse360.group2.Pizza;
import com.asu.edu.cse360.group2.Pizza.Toppings;
import com.asu.edu.cse360.group2.Pizza.Types;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    /**
     * Overrides application start procedure by initializing a new scene with
     * desired window width and height
     * 
     * @param stage Stage which to render to
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("customerhome"), 640, 480);
        stage.setTitle("Sun Devil Pizza");
        stage.getIcons().add(new Image(App.class.getResource("Images/pitchfork.png").toString()));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the root of the main application scene to the provided fxml file under
     * resources
     * 
     * @param fxml String representing the fxml file name to be loaded
     * @throws IOException
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads fxml file with the provided name
     * 
     * @param fxml String representing the fxml file name to be loaded
     * @return fxml loaded object
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void writeTableToFile(Hashtable h, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(h);
            oos.flush();
        }

    }

    public static Hashtable readTableFromFile(File file) throws IOException, ClassNotFoundException {
        Hashtable hashtable = null;
        try (FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            hashtable = (Hashtable) ois.readObject();
        }
        return hashtable;
    }

    /**
     * Main execution of the Pizza application
     * 
     * @param args System args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File ordersFile = new File("orders.bin");
        File newOrdersFile = new File("newOrders.bin");
        File approveOrdersFile = new File("approveOrders.bin");
        File doneOrdersfFile = new File("doneOrders.bin");
        File compFile = new File("completeOrders.bin");

        AppState.orders = readTableFromFile(ordersFile);
        AppState.newOrders = readTableFromFile(newOrdersFile);
        AppState.approvedOrders = readTableFromFile(approveOrdersFile);
        AppState.doneOrders = readTableFromFile(doneOrdersfFile);
        AppState.completeOrders = readTableFromFile(compFile);

        launch();

        writeTableToFile(AppState.orders, ordersFile);
        writeTableToFile(AppState.newOrders, newOrdersFile);
        writeTableToFile(AppState.approvedOrders, approveOrdersFile);
        writeTableToFile(AppState.doneOrders, doneOrdersfFile);
        writeTableToFile(AppState.completeOrders, compFile);

        /*
         * ArrayList<Pizza> list = new ArrayList<>();
         * ArrayList<Toppings> toppings = new ArrayList<>();
         * toppings.add(Toppings.MUSHROOM);
         * Pizza e = new Pizza(Types.CHEESE, toppings);
         * list.add(e);
         * 
         * Order o = new Order(list, 1234567890, "3333 Temp Addr");
         * File file = new File("testOrd.bin");
         * 
         * 
         * System.out.println(o.getOrderNumber());
         * 
         * Order.writeOrdertoFile(o, file);
         * 
         * System.out.println("PhaseOne Fin.");
         * 
         * 
         * 
         * 
         * Order n = Order.readOrderFromFile(file);
         * System.out.println(n.getOrderNumber());
         * 
         * System.out.println("fin.");
         */
    }

}
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

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

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
        scene = new Scene(loadFXML("customerhome"), 600, 600);
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

    /**
     * Main execution of the Pizza application
     * 
     * @param args System args
     */
    public static void main(String[] args) {
        launch();
        ArrayList<Toppings> list = new ArrayList<>();
        list.add(Toppings.EXTRA_CHEESE);
        Pizza p = new Pizza(Types.CHEESE, list);
        System.out.println(Pizza.serializeToJSON(p));
    }

    
}
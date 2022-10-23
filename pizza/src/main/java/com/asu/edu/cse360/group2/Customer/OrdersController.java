/**
 * Provides functionality for all Orders screen UI elements
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2.Customer;

// application
import com.asu.edu.cse360.group2.App;
import com.asu.edu.cse360.group2.AppState;
import com.asu.edu.cse360.group2.Pizza;

// general imports
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.util.Pair;
import java.util.Optional;
import java.util.ArrayList;

// javafx objects
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class OrdersController {
    // local storage of pizza list
    private ObservableList<Pizza> pizzaList;
    private Pizza selectedPizza = null;

    @FXML
    private Text pizzaID;
    @FXML
    private Text pizzaType;
    @FXML
    private Text pizzaToppings;

    @FXML
    private TableView<Pizza> pizzas;

    @FXML
    private TableColumn<Pizza, String> pizzaColumn;

    public void updateText(Pizza oldSelection, Pizza newSelection) {
        if (newSelection == null) {
            pizzaID.setText("");
            pizzaType.setText("");
            pizzaToppings.setText("");
            selectedPizza = null;
            return;
        }

        // newSelection stores new pizza data
        pizzaID.setText("");

        String typeString = "Type: ";
        String toppingsString = "Toppings";

        if (newSelection.getType() == Pizza.Types.PEPPERONI) {
            typeString += "Pepperoni Pizza";
        } else if (newSelection.getType() == Pizza.Types.SAUSAGE) {
            typeString += "Sausage Pizza";
        } else {
            typeString += "Cheese Pizza";
        }

        for (int i = 0; i < newSelection.getToppings().size(); i++) {
            if (newSelection.getToppings().get(i) == Pizza.Toppings.MUSHROOM) {
                toppingsString += "; mushroom";
            } else if (newSelection.getToppings().get(i) == Pizza.Toppings.ONION) {
                toppingsString += "; onion";
            } else if (newSelection.getToppings().get(i) == Pizza.Toppings.OLIVES) {
                toppingsString += "; olives";
            } else if (newSelection.getToppings().get(i) == Pizza.Toppings.EXTRA_CHEESE) {
                toppingsString += "; extra cheese";
            }
        }

        pizzaType.setText(typeString);
        pizzaToppings.setText(toppingsString);

        selectedPizza = newSelection;
    }

    @FXML
    public void initialize() {
        // this function is needed to load elements into the table when the view loads
        pizzaColumn.setCellValueFactory(new PropertyValueFactory<Pizza, String>("name"));
        if (AppState.CustomerState.pizzaList != null) {
            pizzas.setItems(AppState.CustomerState.pizzaList);
            pizzaList = AppState.CustomerState.pizzaList;
        } else {
            pizzas.getItems().clear();
            pizzaList = pizzas.getItems();
        }

        pizzas.getSelectionModel().selectedItemProperty().addListener((observableList, oldSelection, newSelection) -> {
            updateText(oldSelection, newSelection);
        });
    }

    @FXML
    public void addPizza() {
        // Create the custom dialog.
        Dialog<Pair<Pizza.Types, ArrayList<Pizza.Toppings>>> dialog = new Dialog<>();
        dialog.setTitle("Add Pizza");

        // Set the button types.
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 10, 10));

        Label typeLabel = new Label("Pizza Type: ");
        Label toppingLabel = new Label("Pizza Toppings: ");

        // Type buttons
        ToggleGroup typeToggle = new ToggleGroup();
        RadioButton typePepperoni = new RadioButton("Pepperoni");
        RadioButton typeSausage = new RadioButton("Sausage");
        RadioButton typeCheese = new RadioButton("Cheese");
        typePepperoni.setToggleGroup(typeToggle);
        typeSausage.setToggleGroup(typeToggle);
        typeCheese.setToggleGroup(typeToggle);
        HBox typeButtons = new HBox(typePepperoni, typeSausage, typeCheese);
        typeButtons.setSpacing(2);

        // Topping check boxes
        CheckBox toppingMushroom = new CheckBox("Mushroom");
        CheckBox toppingOnion = new CheckBox("Onion");
        CheckBox toppingOlives = new CheckBox("Olives");
        CheckBox toppingExtraCheese = new CheckBox("Extra Cheese");
        HBox toppingButtons = new HBox(toppingMushroom, toppingOnion, toppingOlives, toppingExtraCheese);
        toppingButtons.setSpacing(2);

        // Gridpane updates
        gridPane.add(typeLabel, 0, 0);
        gridPane.add(typeButtons, 1, 0);
        gridPane.add(toppingLabel, 0, 1);
        gridPane.add(toppingButtons, 1, 1);

        dialog.getDialogPane().setContent(gridPane);

        // request focus for dialog element __
        // Platform.runLater(() -> __.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                RadioButton selected = (RadioButton) typeToggle.getSelectedToggle();
                if (selected == null) {
                    Alert alert = new Alert(AlertType.ERROR, "Please select a pizza type");
                    alert.show();
                    return null;
                }

                Pizza.Types type;
                if (selected.getText().compareTo(typePepperoni.getText()) == 0) {
                    type = Pizza.Types.PEPPERONI;
                } else if (selected.getText().compareTo(typeCheese.getText()) == 0) {
                    type = Pizza.Types.CHEESE;
                } else {
                    type = Pizza.Types.SAUSAGE;
                }

                ArrayList<Pizza.Toppings> toppings = new ArrayList<Pizza.Toppings>();
                if (toppingMushroom.isSelected()) {
                    toppings.add(Pizza.Toppings.MUSHROOM);
                }
                if (toppingOlives.isSelected()) {
                    toppings.add(Pizza.Toppings.OLIVES);
                }
                if (toppingOnion.isSelected()) {
                    toppings.add(Pizza.Toppings.ONION);
                }
                if (toppingExtraCheese.isSelected()) {
                    toppings.add(Pizza.Toppings.EXTRA_CHEESE);
                }

                return new Pair<>(type, toppings);
            }
            return null;
        });

        Optional<Pair<Pizza.Types, ArrayList<Pizza.Toppings>>> result = dialog.showAndWait();

        result.ifPresent(pair -> {
            pizzaList.add(new Pizza(pair.getKey(), pair.getValue()));
        });
    }

    @FXML
    public void editPizza() {
        if (selectedPizza == null) {
            Alert alert = new Alert(AlertType.ERROR, "No pizza selected");
            alert.show();
            return;
        }

        // Create the custom dialog.
        Dialog<Pair<Pizza.Types, ArrayList<Pizza.Toppings>>> dialog = new Dialog<>();
        dialog.setTitle("Edit Pizza");

        // Set the button types.
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType deleteButton = new ButtonType("Delete", ButtonData.OTHER);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, deleteButton, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 10, 10));

        Label typeLabel = new Label("Pizza Type: ");
        Label toppingLabel = new Label("Pizza Toppings: ");

        // Type buttons
        ToggleGroup typeToggle = new ToggleGroup();
        RadioButton typePepperoni = new RadioButton("Pepperoni");
        RadioButton typeSausage = new RadioButton("Sausage");
        RadioButton typeCheese = new RadioButton("Cheese");
        typePepperoni.setToggleGroup(typeToggle);
        typeSausage.setToggleGroup(typeToggle);
        typeCheese.setToggleGroup(typeToggle);
        HBox typeButtons = new HBox(typePepperoni, typeSausage, typeCheese);
        typeButtons.setSpacing(2);

        if (selectedPizza.getType() == Pizza.Types.PEPPERONI) {
            typePepperoni.setSelected(true);
        } else if (selectedPizza.getType() == Pizza.Types.SAUSAGE) {
            typeSausage.setSelected(true);
        } else {
            typeCheese.setSelected(true);
        }

        // Topping check boxes
        CheckBox toppingMushroom = new CheckBox("Mushroom");
        CheckBox toppingOnion = new CheckBox("Onion");
        CheckBox toppingOlives = new CheckBox("Olives");
        CheckBox toppingExtraCheese = new CheckBox("Extra Cheese");
        HBox toppingButtons = new HBox(toppingMushroom, toppingOnion, toppingOlives, toppingExtraCheese);
        toppingButtons.setSpacing(2);

        if (selectedPizza.getToppings().contains(Pizza.Toppings.MUSHROOM)) {
            toppingMushroom.setSelected(true);
        }
        if (selectedPizza.getToppings().contains(Pizza.Toppings.ONION)) {
            toppingOnion.setSelected(true);
        }
        if (selectedPizza.getToppings().contains(Pizza.Toppings.OLIVES)) {
            toppingOlives.setSelected(true);
        }
        if (selectedPizza.getToppings().contains(Pizza.Toppings.EXTRA_CHEESE)) {
            toppingExtraCheese.setSelected(true);
        }

        // Gridpane updates
        gridPane.add(typeLabel, 0, 0);
        gridPane.add(typeButtons, 1, 0);
        gridPane.add(toppingLabel, 0, 1);
        gridPane.add(toppingButtons, 1, 1);

        dialog.getDialogPane().setContent(gridPane);

        // request focus for dialog element __
        // Platform.runLater(() -> __.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                RadioButton selected = (RadioButton) typeToggle.getSelectedToggle();
                if (selected == null) {
                    Alert alert = new Alert(AlertType.ERROR, "Please select a pizza type");
                    alert.show();
                    return null;
                }

                Pizza.Types type;
                if (selected.getText().compareTo(typePepperoni.getText()) == 0) {
                    type = Pizza.Types.PEPPERONI;
                } else if (selected.getText().compareTo(typeCheese.getText()) == 0) {
                    type = Pizza.Types.CHEESE;
                } else {
                    type = Pizza.Types.SAUSAGE;
                }

                ArrayList<Pizza.Toppings> toppings = new ArrayList<Pizza.Toppings>();
                if (toppingMushroom.isSelected()) {
                    toppings.add(Pizza.Toppings.MUSHROOM);
                }
                if (toppingOlives.isSelected()) {
                    toppings.add(Pizza.Toppings.OLIVES);
                }
                if (toppingOnion.isSelected()) {
                    toppings.add(Pizza.Toppings.ONION);
                }
                if (toppingExtraCheese.isSelected()) {
                    toppings.add(Pizza.Toppings.EXTRA_CHEESE);
                }

                return new Pair<>(type, toppings);
            }

            if (dialogButton == deleteButton) {
                pizzaList.remove(selectedPizza);
                updateText(null, null);
            }
            return null;
        });

        Optional<Pair<Pizza.Types, ArrayList<Pizza.Toppings>>> result = dialog.showAndWait();

        result.ifPresent(pair -> {
            selectedPizza.setType(pair.getKey());
            selectedPizza.setToppings(pair.getValue());
            pizzaList.add(new Pizza(null, null));
            pizzaList.remove(pizzaList.size() - 1);
            updateText(selectedPizza, selectedPizza);
        });
    }

    @FXML
    public void checkout() throws IOException {
        AppState.CustomerState.pizzaList = pizzaList;
        App.setRoot("customerpayment");
    }

    @FXML
    public void cancel() throws IOException {
        AppState.CustomerState.pizzaList = null;
        App.setRoot("customerhome");
    }
}

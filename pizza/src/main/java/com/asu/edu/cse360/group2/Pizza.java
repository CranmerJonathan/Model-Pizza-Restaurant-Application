/**
 * Stores all relevant parts of a pizza
 * @author Eron Ristich
 * @date 10/22/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2;

// general imports


import java.util.ArrayList;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;


public class Pizza implements Serializable{
    // type of pizza
    public static enum Types {
        PEPPERONI, SAUSAGE, CHEESE
    }

    // set of toppings
    public static enum Toppings {
        MUSHROOM, ONION, OLIVES, EXTRA_CHEESE
    }

    // pizza modifiers
    private Types type;
    private ArrayList<Toppings> toppings;

    public Pizza(Types type, ArrayList<Toppings> toppings) {
        this.type = type;
        this.toppings = toppings;
    }

    // returns pizza type
    public Types getType() {
        return type;
    }

    // returns pizza toppings
    public ArrayList<Toppings> getToppings() {
        return toppings;
    }

    // utility for table view
    public String getName() {
        String returned = "";
        if (type == Types.PEPPERONI) {
            returned += "Pepperoni Pizza";
        } else if (type == Types.SAUSAGE) {
            returned += "Sausage Pizza";
        } else {
            returned += "Cheese Pizza";
        }

        for (int i = 0; i < toppings.size(); i++) {
            if (toppings.get(i) == Toppings.MUSHROOM) {
                returned += "; mushroom";
            } else if (toppings.get(i) == Toppings.ONION) {
                returned += "; onion";
            } else if (toppings.get(i) == Toppings.OLIVES) {
                returned += "; olives";
            } else if (toppings.get(i) == Toppings.EXTRA_CHEESE) {
                returned += "; extra cheese";
            }
        }

        return returned;
    }

    // add/remove toppings
    public void toggleTopping(Toppings topping) {
        if (toppings.contains(topping)) {
            toppings.remove(topping);
        } else {
            toppings.add(topping);
        }
    }

    // change type of pizza
    public void setType(Types type) {
        this.type = type;
    }

    // set toppings
    public void setToppings(ArrayList<Toppings> toppings) {
        this.toppings = toppings;
    }

    // TODO
    // accepts a pizza object and serializes it in JSON format returned as a string
    public static String serialization(Pizza pizza) {
        Gson gson = new Gson();
        String p = gson.toJson(pizza);
        
        return p;   
    }

    // TODO
    // accepts a file path (URL) and deserializes it and returns a Pizza object
    public static Pizza deserializeFromJSON(String string) {
        return null;
    }

    
}

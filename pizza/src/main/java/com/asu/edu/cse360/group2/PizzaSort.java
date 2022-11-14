/**
 * Sorting algorithms given an array list of pizzas
 * @author Jonathan Cranmer
 * @date 11/10/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2;

import java.util.List;

public class PizzaSort {
    private List<Pizza> pizzas;

    PizzaSort() {
        this.pizzas = null;
    }

    PizzaSort(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Pizza> sortPizza(List<Pizza> pizzas) {
        int n = pizzas.size();
        for (int i = 1; i < n; ++i) {
            Pizza key = pizzas.get(i);
            int j = i - 1;

            /*
             * Move elements of arr[0..i-1], that are
             * greater than key, to one position ahead
             * of their current position
             */
            while (j >= 0 && (pizzas.get(j).getType()).compareTo(key.getType()) > 0) {
                pizzas.set(j + 1, pizzas.get(j));
                j = j - 1;
            }
            if ((pizzas.get(j).getType()).compareTo(key.getType()) == 0) {
                while (j >= 0 && pizzas.get(j).getToppings().size() > key.getToppings().size()) {
                    pizzas.set(j + 1, pizzas.get(j));
                    j = j - 1;
                }
            }
            pizzas.set(j + 1, key);
        }
        return pizzas;
    }
}

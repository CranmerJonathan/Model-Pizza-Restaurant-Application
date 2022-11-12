/**
 * Sorting algorithms given an array list of orders
 * @author Jonathan Cranmer
 * @date 11/10/22
 * @version 1.0
 */

package com.asu.edu.cse360.group2;

import java.util.ArrayList;

public class OrderSort {
	private ArrayList<Order> orders;

	public OrderSort() {
		this.orders = null;
	}

	public OrderSort(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public ArrayList<Order> sortOrdersTime() {
		int n = orders.size();
		for (int i = 1; i < n; ++i) {
			Order key = orders.get(i);
			int j = i - 1;

			while (j >= 0 && orders.get(j).getOrderNumber() > key.getOrderNumber()) {
				orders.set(j + 1, orders.get(j));
				j = j - 1;
			}
			orders.set(j + 1, key);
		}

		for (int i = 0; i < n; i++) {
			PizzaSort sort = new PizzaSort();
			sort.sortPizza(orders.get(i).getPizzas());
		}
		return orders;
	}

	public ArrayList<Order> sortOrdersSize() {
		int n = orders.size();
		for (int i = 1; i < n; ++i) {
			Order key = orders.get(i);
			int j = i - 1;

			while (j >= 0 && orders.get(j).getPizzas().size() > key.getPizzas().size()) {
				orders.set(j + 1, orders.get(j));
				j = j - 1;
			}
			orders.set(j + 1, key);
		}

		for (int i = 0; i < n; i++) {
			PizzaSort sort = new PizzaSort();
			sort.sortPizza(orders.get(i).getPizzas());
		}
		return orders;
	}

	public ArrayList<Order> sortOrdersState() {
		int n = orders.size();
		for (int i = 1; i < n; ++i) {
			Order key = orders.get(i);
			int j = i - 1;

			while (j >= 0 && (orders.get(j)).getStateNumber() > key.getStateNumber()) {
				orders.set(j + 1, orders.get(j));
				j = j - 1;
			}
			orders.set(j + 1, key);
		}

		for (int i = 0; i < n; i++) {
			PizzaSort sort = new PizzaSort();
			sort.sortPizza(orders.get(i).getPizzas());
		}
		return orders;
	}
}

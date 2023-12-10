package com.example.ru_pizza_app;

import java.util.ArrayList;
import android.content.Context;

/**
* This class is an abstract class that represents a pizza and it's properties
* @authors Yasasvi Tallapaneni, Pranav Gummaluri
*/
public abstract class Pizza {
    protected ArrayList<Topping> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
    * This method is an abstract method to calculate the price of a pizza
    * @return a double representing the price of the pizza
    */
    public abstract double price();
    public abstract String getSauce();
    public abstract String getName();
    public String getToppings() {
        StringBuilder toppingsString = new StringBuilder();

        for (int i = 0; i < toppings.size(); i++) {
            toppingsString.append(toppings.get(i));

            if (i < toppings.size() - 1) {
                toppingsString.append(", ");
            }
        }

        return toppingsString.toString();
    }


}

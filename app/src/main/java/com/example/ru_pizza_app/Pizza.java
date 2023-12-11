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

    /**
    * This method is an abstract getter method to return the sauce of a pizza
    * @return a String representing the sauce of the pizza
    */
    public abstract String getSauce();

    /**
    * This method is an abstract getter method to return the name of a pizza
    * @return a String representing the name of the pizza
    */
    public abstract String getName();

    /**
    * This method lists the toppings of a pizza
    * @return a String representing the toppings of the pizza
    */
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

    /**
    * This method is a toString which highlights the details of the pizza
    * @return a String representing the name of the pizza
    */
    @Override
    public String toString() {
        StringBuilder pizzaDetails = new StringBuilder();
        pizzaDetails.append("[")
                .append(this.getClass().getSimpleName())
                .append("] ")
                .append(getToppings())
                .append(" ")
                .append(this.size)
                .append(" ")
                .append(this.extraSauce ? "extra sauce " : "")
                .append(this.extraCheese ? "extra cheese " : "")
                .append("$")
                .append(String.format("%.2f", this.price()));

        return pizzaDetails.toString();
    }


}

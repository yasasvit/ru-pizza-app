package com.example.ru_pizza_app;

import java.util.ArrayList;

/**
* This class is for building custom pizzas with different sizes, sauces, toppings, and extra options
* @authors Yasasvi Tallapaneni, Pranav Gummaluri
*/
public class BuildYourOwn extends Pizza {
    public BuildYourOwn(Size size, Sauce sauce, ArrayList<Topping> selectedToppings, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.toppings = selectedToppings;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
    * This method manages the price of the pizza and accounts for size changes and optional extras 
    * @return a double which represents the price of optioned pizza
    */
    @Override
    public double price() {
        double basePrice = 8.99;
        switch (size) {
            case medium:
                basePrice += 2;
                break;
            case large:
                basePrice += 4;
                break;
        }
        int defaultToppingsCount = 3;
        int remainingToppings = toppings.size() - defaultToppingsCount;
        double toppingPrice = 1.49;
        basePrice += (remainingToppings * toppingPrice);
        if (extraCheese) {
            basePrice += 1;
        }
        if (extraSauce) {
            basePrice += 1;
        }
        return basePrice;
    }

    /**
    * This method is a getter for a sauce object
    * @return a String which represents the sauce of optioned pizza
    */
    @Override
    public String getSauce() {
        return null;
    }

    /**
    * This method is a getter for a name object
    * @return a String which represents the name of the pizza
    */
    @Override
    public String getName() {
        return null;
    }
}

package com.example.ru_pizza_app;

import java.util.ArrayList;

/**
 * This class represents the Detroit specialty pizza and calculates the price with addons
 * @authors Yasasvi Tallapaneni, Pranav Gummaluri
 */
public class Detroit extends Pizza {
    public Detroit(Size size, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.toppings = new ArrayList<>();
        this.toppings.add(Topping.Sausage);
        this.toppings.add(Topping.Onion);
        this.toppings.add(Topping.Mushroom);
        this.sauce = Sauce.tomato;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     * This method calculates the price of the Detroit with any addons
     * @return a double representing price with any addons
     */
    @Override
    public double price() {
        double basePrice = 13.99;
        switch (size) {
            case medium:
                basePrice += 2;
                break;
            case large:
                basePrice += 4;
                break;
        }
        if (extraCheese) {
            basePrice += 1;
        }
        if (extraSauce) {
            basePrice += 1;
        }
        return basePrice;
    }

    /*
    * A getter method for the name of the pizza
    * @return a string representing pizza type
    */
    @Override
    public String getName() {
        return "Detroit";
    }

    /*
    * A getter method for the sauce of the pizza
    * @return a string representing sauce type
    */
    @Override
    public String getSauce(){
        return "Tomato";
    }
}

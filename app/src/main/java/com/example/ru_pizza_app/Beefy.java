package com.example.ru_pizza_app;

import java.util.ArrayList;

/**
 * This class represents the Beefy specialty pizza and calculates the price with addons
 * @authors Yasasvi Tallapaneni, Pranav Gummaluri
 */
public class Beefy extends Pizza {
    public Beefy(Size size, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.toppings = new ArrayList<>();
        this.toppings.add(Topping.Beef);
        this.toppings.add(Topping.Onion);
        this.sauce = Sauce.tomato;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     * This method calculates the price of the Beefy with any addons
     * @return a double representing price with any addons
     */
    @Override
    public double price() {
        double basePrice = 12.99;
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
    @Override
    public String getName() {
        return "Beefy";
    }
    @Override
    public String getSauce(){
        return "Tomato";
    }
}

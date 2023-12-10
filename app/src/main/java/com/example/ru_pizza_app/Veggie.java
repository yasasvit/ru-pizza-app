package com.example.ru_pizza_app;

import java.util.ArrayList;

/**
 * This class represents the Veggie specialty pizza and calculates the price with addons
 * @authors Yasasvi Tallapaneni, Pranav Gummaluri
 */
public class Veggie extends Pizza {
    public Veggie(Size size, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.toppings = new ArrayList<>();
        this.toppings.add(Topping.BlackOlives);
        this.toppings.add(Topping.Mushroom);
        this.toppings.add(Topping.Onion);
        this.toppings.add(Topping.GreenPepper);
        this.sauce = Sauce.tomato;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     * This method calculates the price of the Veggie with any addons
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
    @Override
    public String getName() {
        return "Veggie";
    }
    @Override
    public String getSauce(){
        return "Tomato";
    }
}

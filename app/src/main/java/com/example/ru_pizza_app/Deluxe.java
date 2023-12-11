package com.example.ru_pizza_app;

import java.util.ArrayList;

/**
* This class represents the Deluxe specialty pizza and its base price
* @authors Pranav Gummaluri, Yasasvi Tallapaneni
*/
public class Deluxe extends Pizza {
    public Deluxe(Size size, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.toppings = new ArrayList<>();
        this.toppings.add(Topping.Sausage);
        this.toppings.add(Topping.Pepperoni);
        this.toppings.add(Topping.GreenPepper);
        this.toppings.add(Topping.Onion);
        this.toppings.add(Topping.Mushroom);
        this.sauce = Sauce.tomato;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
    * This method gives us the base price of the Deluxe pizza, will increase the price with each addon
    * @return a double which represents the price of the optioned pizza
    */
    @Override
    public double price() {
        double basePrice = 14.99;
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
        return "Deluxe";
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

package com.example.ru_pizza_app;

import java.util.ArrayList;

public class SpecialtyPizza {
    String name;
    ArrayList<Topping> toppings;
    int image;
    String sauce;
    double price;
    public SpecialtyPizza(String name, ArrayList<Topping> toppings, int image, String sauce, double price) {
        this.name = name;
        this.toppings = toppings;
        this.image = image;
        this.sauce = sauce;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

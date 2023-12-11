package com.example.ru_pizza_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ru_pizza_app.Pizza;

import java.util.List;

/*
* This class is the adapter for the RecyclerView that displays a list of pizzas
* @authors Pranav Gummaluri, Yasasvi Tallapaneni
*/
public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    private Context context;
    private List<Pizza> pizzaList;

    /*
    * Constructor
    */
    public PizzaAdapter(Context context, List<Pizza> pizzaList) {
        this.context = context;
        this.pizzaList = pizzaList;
    }

    /*
    * This method creates the layout for a single pizza item and creates a ViewHolder for it
    * @return a new instance of the ViewHolder class
    */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pizza_item, parent, false);
        return new ViewHolder(view);
    }

    /*
    * This method binds the data of a specific Pizza to the views in a ViewHolder
    * @param holder which represents the ViewHolder for the current item
    * @param position which represents the position of the item in the data set
    */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);

        holder.textViewPizzaName.setText(pizza.getName());
        String formattedPrice = String.format("$%.2f", pizza.price());
        holder.textViewPizzaPrice.setText(formattedPrice);
        holder.textViewSauce.setText("Sauce: " + pizza.getSauce());
        holder.textViewToppings.setText("Toppings: " + pizza.getToppings());

        holder.imageViewPizza.setImageResource(getImageResourceId(pizza.getName()));
    }

    /*
    * This method returns the total number of items in the data set
    * @return an int representing list size
    */
    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    /*
    * This class represents a single item view in the RecyclerView
    */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPizza;
        TextView textViewPizzaName, textViewPizzaPrice, textViewSauce, textViewToppings;

        /*
        * Constructor
        */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPizza = itemView.findViewById(R.id.imageViewPizza);
            textViewPizzaName = itemView.findViewById(R.id.textViewPizzaName);
            textViewPizzaPrice = itemView.findViewById(R.id.textViewPizzaPrice);
            textViewSauce = itemView.findViewById(R.id.textViewSauce);
            textViewToppings = itemView.findViewById(R.id.textViewToppings);
        }
    }

    /*
    * This method retrieves the resource ID of the pizza image based on its name
    * @param pizzaName representing name of pizza
    * @return an int representing the ID of the pizza
    */
    private int getImageResourceId(String pizzaName) {
        String imageName = pizzaName.toLowerCase().replace(" ", "_") + "_img";
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }
}

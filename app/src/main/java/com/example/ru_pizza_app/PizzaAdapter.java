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

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHolder> {

    private Context context;
    private List<Pizza> pizzaList;

    public PizzaAdapter(Context context, List<Pizza> pizzaList) {
        this.context = context;
        this.pizzaList = pizzaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pizza_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pizza pizza = pizzaList.get(position);

        // Set the values for each view in the ViewHolder
        holder.textViewPizzaName.setText(pizza.getName());
        String formattedPrice = String.format("$%.2f", pizza.price());
        holder.textViewPizzaPrice.setText(formattedPrice);
        holder.textViewSauce.setText("Sauce: " + pizza.getSauce());
        holder.textViewToppings.setText("Toppings: " + pizza.getToppings());

        // Set the image using your preferred image loading library or method
        holder.imageViewPizza.setImageResource(getImageResourceId(pizza.getName()));
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPizza;
        TextView textViewPizzaName, textViewPizzaPrice, textViewSauce, textViewToppings;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPizza = itemView.findViewById(R.id.imageViewPizza);
            textViewPizzaName = itemView.findViewById(R.id.textViewPizzaName);
            textViewPizzaPrice = itemView.findViewById(R.id.textViewPizzaPrice);
            textViewSauce = itemView.findViewById(R.id.textViewSauce);
            textViewToppings = itemView.findViewById(R.id.textViewToppings);
        }
    }

    private int getImageResourceId(String pizzaName) {
        String imageName = pizzaName.toLowerCase().replace(" ", "_") + "_img";
        return context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
    }
}

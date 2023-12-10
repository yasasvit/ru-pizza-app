package com.example.ru_pizza_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import androidx.activity.ComponentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SpecialtyPizzasActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialtypizzas);

        List<Pizza> specialtyPizzaList = createSpecialtyPizzas();

        RecyclerView recyclerView = findViewById(R.id.specialtyRecycler);

        // Create and set the layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Create and set the adapter
        PizzaAdapter pizzaAdapter = new PizzaAdapter(this, specialtyPizzaList);
        recyclerView.setAdapter(pizzaAdapter);


        String[] pizzaSizes = {"Small", "Medium", "Large"};
        String[] pizzaTypes = {"Cheese", "Pepperoni", "Seafood", "Meatzza", "Supreme", "Deluxe", "Veggie","Beefy", "Italiana", "Detroit"};

        Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pizzaSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinnerPizzaTypes = findViewById(R.id.spinnerPizzaTypes);
        ArrayAdapter<String> pizzaTypesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pizzaTypes);
        pizzaTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPizzaTypes.setAdapter(pizzaTypesAdapter);    }

    private List<Pizza> createSpecialtyPizzas() {
        List<Pizza> specialtyPizzas = new ArrayList<>();

        // Create one instance of each specialty pizza and add it to the list
        specialtyPizzas.add(new Deluxe(Size.medium, false, false));
        specialtyPizzas.add(new Meatzza(Size.medium, false, false));
        specialtyPizzas.add(new Pepperoni(Size.medium, false, false));
        specialtyPizzas.add(new Seafood(Size.medium, false, false));
        specialtyPizzas.add(new Supreme(Size.medium, false, false));
        specialtyPizzas.add(new Cheese(Size.medium, false, false));
        specialtyPizzas.add(new Beefy(Size.medium, false, false));
        specialtyPizzas.add(new Italiana(Size.medium, false, false));
        specialtyPizzas.add(new Veggie(Size.medium, false, false));
        specialtyPizzas.add(new Detroit(Size.medium, false, false));

        return specialtyPizzas;
    }
}

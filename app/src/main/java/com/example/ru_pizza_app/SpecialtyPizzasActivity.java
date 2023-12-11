package com.example.ru_pizza_app;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*
* This class displays a list of specialty pizzas using a RecyclerView
* @authors Pranav Gummaluri, Yasasvi Tallapaneni
*/
public class SpecialtyPizzasActivity extends ComponentActivity {
    private PizzaOrderService pizzaOrderService = PizzaOrderService.getInstance();
    private static final int MIN_TOPPINGS = 3;

    private Spinner sizesSpinner;
    private Spinner pizzaTypesSpinner;
    private CheckBox extraCheeseCheckbox;
    private CheckBox extraSauceCheckbox;
    private TextView priceTextView;

    /*
    * This method initializes the RecyclerView and sets its layout manager
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialtypizzas);

        List<Pizza> specialtyPizzaList = createSpecialtyPizzas();

        RecyclerView recyclerView = findViewById(R.id.specialtyRecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        PizzaAdapter pizzaAdapter = new PizzaAdapter(this, specialtyPizzaList);
        recyclerView.setAdapter(pizzaAdapter);

        sizesSpinner = findViewById(R.id.spinnerSizes);
        pizzaTypesSpinner = findViewById(R.id.spinnerPizzaTypes);
        extraCheeseCheckbox = findViewById(R.id.extraCheeseCheckbox);
        extraSauceCheckbox = findViewById(R.id.extraSauceCheckbox);
        priceTextView = findViewById(R.id.subtotalField);

        String[] pizzaSizes = {"Small", "Medium", "Large"};
        String[] pizzaTypes = {"Cheese", "Pepperoni", "Seafood", "Meatzza", "Supreme", "Deluxe", "Veggie", "Beefy", "Italiana", "Detroit"};


        ArrayAdapter<String> sizesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pizzaSizes);
        sizesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizesSpinner.setAdapter(sizesAdapter);

        ArrayAdapter<String> pizzaTypesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pizzaTypes);
        pizzaTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaTypesSpinner.setAdapter(pizzaTypesAdapter);


        setListeners();
    }

    /*
    * This method sets OnItemSelectedListener so the spinners update the price when selections change
    */
    private void setListeners() {
        sizesSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                updatePrice();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });
        pizzaTypesSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                updatePrice();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });


        extraCheeseCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());
        extraSauceCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());

        Button orderButton = findViewById(R.id.button8);

        orderButton.setOnClickListener(v -> placeOrder());
    }

    /*
    * This method creates a Pizza object based on user selections and adds it to the order
    */
    private void placeOrder() {
        String selectedSize = sizesSpinner.getSelectedItem().toString();

        if (selectedSize == null) {
            showAlert("Size must be chosen.");
            return;
        }

        String selectedPizzaType = pizzaTypesSpinner.getSelectedItem().toString();

        boolean isExtraSauce = extraSauceCheckbox.isChecked();
        boolean isExtraCheese = extraCheeseCheckbox.isChecked();

        Pizza pizza = PizzaMaker.createPizza(selectedPizzaType.toLowerCase(), Size.valueOf(selectedSize.toLowerCase()), null, null, isExtraSauce, isExtraCheese);
        pizzaOrderService.addPizzaToOrder(pizza);
        Toast.makeText(this, "Pizza added!", Toast.LENGTH_SHORT).show();

    }

    /*
    * This method calculates and updates the displayed price based on user selections
    */
    private void updatePrice() {
        double basePrice;
        switch (pizzaTypesSpinner.getSelectedItem().toString()) {
            case "Cheese":
                basePrice = 8.99;
                break;
            case "Pepperoni":
                basePrice = 10.99;
                break;
            case "Seafood":
                basePrice = 17.99;
                break;
            case "Meatzza":
                basePrice = 16.99;
                break;
            case "Supreme":
            case "Italiana":
                basePrice = 15.99;
                break;
            case "Deluxe":
                basePrice = 14.99;
                break;
            case "Veggie":
            case "Detroit":
                basePrice = 13.99;
                break;
            case "Beefy":
                basePrice = 12.99;
                break;
            default:
                basePrice = 0.0;
        }
        switch (sizesSpinner.getSelectedItem().toString()) {
            case "Small":
                break;
            case "Medium":
                basePrice += 2.0;
                break;
            case "Large":
                basePrice += 4.0;
                break;
        }

        if (extraCheeseCheckbox.isChecked()) {
            basePrice += 1.0;
        }

        if (extraSauceCheckbox.isChecked()) {
            basePrice += 1.0;
        }

        priceTextView.setText(String.format("$%.2f", basePrice));
    }

    /*
    * This method creates a list of specialty pizzas to be displayed in the RecyclerView
    */
    private List<Pizza> createSpecialtyPizzas() {
        List<Pizza> specialtyPizzas = new ArrayList<>();

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

    /*
    * This method Displays an alert dialog with the provided message
    * @param message representing the message to be displayed
    */
    private void showAlert(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(message)
                .setPositiveButton("OK", (dialog, id) -> {
                });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}

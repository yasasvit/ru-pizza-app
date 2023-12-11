package com.example.ru_pizza_app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildYourOwnActivity extends ComponentActivity {
    private PizzaOrderService pizzaOrderService = PizzaOrderService.getInstance();

    private static final int MAX_TOPPINGS = 7;
    private static final int MIN_TOPPINGS = 3;

    private ListView allToppingsListView;
    private ListView selectedToppingsListView;
    private ArrayAdapter<String> allToppingsAdapter;
    private ArrayAdapter<String> selectedToppingsAdapter;

    private Spinner sizeSpinner;
    private RadioButton tomatoButton;
    private RadioButton alfredoButton;
    private CheckBox extraCheeseCheckbox;
    private CheckBox extraSauceCheckbox;
    private TextView priceTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buildyourown);

        String[] pizzaSizes = {"Small", "Medium", "Large"};

        Spinner spinner = findViewById(R.id.sizeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pizzaSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayList<String> allToppingsArray = new ArrayList<>(Arrays.asList("Sausage", "Chicken", "Beef", "Ham", "Pepperoni", "Shrimp", "Squid", "CrabMeats", "GreenPepper", "Onion", "Mushroom", "Pineapple", "BlackOlives"));
        ArrayList<String> selectedToppingsArray = new ArrayList<>();

        allToppingsListView = findViewById(R.id.allToppings);
        selectedToppingsListView = findViewById(R.id.selectedToppings);

        allToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, allToppingsArray);
        selectedToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, selectedToppingsArray);

        allToppingsListView.setAdapter(allToppingsAdapter);
        selectedToppingsListView.setAdapter(selectedToppingsAdapter);

        sizeSpinner = findViewById(R.id.sizeSpinner);
        tomatoButton = findViewById(R.id.tomatoButton);
        alfredoButton = findViewById(R.id.alfredoButton);
        extraCheeseCheckbox = findViewById(R.id.extraCheeseCheckbox);
        extraSauceCheckbox = findViewById(R.id.extraSauceCheckbox);
        priceTextField = findViewById(R.id.priceTextfield);


        tomatoButton.setOnClickListener(v -> {
            if (alfredoButton.isChecked()) {
                alfredoButton.setChecked(false);
            }
        });

        alfredoButton.setOnClickListener(v -> {
            if (tomatoButton.isChecked()) {
                tomatoButton.setChecked(false);
            }
        });

        setListeners();

        Button addButton = findViewById(R.id.addButton);
        Button removeButton = findViewById(R.id.removeButton);
        Button orderButton = findViewById(R.id.orderButton);

        addButton.setOnClickListener(v -> addTopping());
        removeButton.setOnClickListener(v -> removeTopping());
        orderButton.setOnClickListener(v -> placeOrder());
    }

    private void setListeners() {
        sizeSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        tomatoButton.setOnClickListener(v -> updatePrice());
        alfredoButton.setOnClickListener(v -> updatePrice());

        tomatoButton.setOnClickListener(v -> {
            if (alfredoButton.isChecked()) {
                alfredoButton.setChecked(false);
            }
        });

        alfredoButton.setOnClickListener(v -> {
            if (tomatoButton.isChecked()) {
                tomatoButton.setChecked(false);
            }
        });

        extraCheeseCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());
        extraSauceCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());

        Button addButton = findViewById(R.id.addButton);
        Button removeButton = findViewById(R.id.removeButton);

        addButton.setOnClickListener(v -> {
            addTopping();
            updatePrice();
        });

        removeButton.setOnClickListener(v -> {
            removeTopping();
            updatePrice();
        });
    }
    private void updatePrice() {
        double basePrice;

        switch (sizeSpinner.getSelectedItem().toString()) {
            case "Small":
                basePrice = 8.99;
                break;
            case "Medium":
                basePrice = 10.99;
                break;
            case "Large":
                basePrice = 12.99;
                break;
            default:
                basePrice = 0.0;
        }

        if (extraCheeseCheckbox.isChecked()) {
            basePrice += 1.0;
        }

        if (extraSauceCheckbox.isChecked()) {
            basePrice += 1.0;
        }

        int numToppings = selectedToppingsAdapter.getCount();
        if (numToppings > MIN_TOPPINGS) {
            basePrice += (numToppings - MIN_TOPPINGS) * 1.49;
        }

        priceTextField.setText(String.format("$%.2f", basePrice));
    }


    private void addTopping() {
        if (selectedToppingsAdapter.getCount() >= MAX_TOPPINGS) {
            showAlert("Maximum toppings reached!");
            return;
        }
        int position = allToppingsListView.getCheckedItemPosition();
        if (position != ListView.INVALID_POSITION) {
            String topping = allToppingsAdapter.getItem(position);
            selectedToppingsAdapter.add(topping);
            allToppingsAdapter.remove(topping);
            updatePrice();
        }
    }

    private void removeTopping() {
        if (selectedToppingsAdapter.getCount() <= MIN_TOPPINGS) {
            showAlert("At least 3 toppings required!");
            return;
        }
        int position = selectedToppingsListView.getCheckedItemPosition();
        if (position != ListView.INVALID_POSITION) {
            String topping = selectedToppingsAdapter.getItem(position);
            allToppingsAdapter.add(topping);
            selectedToppingsAdapter.remove(topping);
            updatePrice();
        }
    }

    private void placeOrder() {
        String selectedSize = sizeSpinner.getSelectedItem().toString();
        String selectedSauce = tomatoButton.isChecked() ? "tomato" : (alfredoButton.isChecked() ? "alfredo" : null);

        if (selectedSize == null || selectedSauce == null) {
            showAlert("Size and sauce must be chosen.");
            return;
        }

        ArrayList<String> selectedToppings = new ArrayList<>();
        for (int i = 0; i < selectedToppingsAdapter.getCount(); i++) {
            selectedToppings.add(selectedToppingsAdapter.getItem(i));
        }

        if (selectedToppings.size() < MIN_TOPPINGS) {
            showAlert("At least 3 toppings must be chosen.");
            return;
        }

        ArrayList<Topping> toppings = new ArrayList<>();
        for (String topping : selectedToppings) {
            toppings.add(Topping.valueOf(topping));
        }

        boolean isExtraSauce = extraSauceCheckbox.isChecked();
        boolean isExtraCheese = extraCheeseCheckbox.isChecked();

        Pizza pizza = PizzaMaker.createPizza("buildyourown", Size.valueOf(selectedSize.toLowerCase()), Sauce.valueOf(selectedSauce), toppings, isExtraSauce, isExtraCheese);

        pizzaOrderService.addPizzaToOrder(pizza);

        Toast.makeText(this, "Pizza added!", Toast.LENGTH_SHORT).show();
    }

    private void showAlert(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(message)
                .setPositiveButton("OK", (dialog, id) -> {
                });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

}
package com.example.ru_pizza_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import android.app.AlertDialog;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildYourOwnActivity extends ComponentActivity {

    private static final int MAX_TOPPINGS = 7;
    private static final int MIN_TOPPINGS = 3;

    private ListView allToppingsListView;
    private ListView selectedToppingsListView;
    private ArrayAdapter<String> allToppingsAdapter;
    private ArrayAdapter<String> selectedToppingsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buildyourown);

        // Assuming you have a list of pizza sizes, replace with your data
        String[] pizzaSizes = {"Small", "Medium", "Large"};

        Spinner spinner = findViewById(R.id.sizeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pizzaSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Assuming you have a list of pizza toppings, replace with your data
        ArrayList<String> allToppingsArray = new ArrayList<>(Arrays.asList("Sausage", "Chicken", "Beef", "Ham", "Pepperoni", "Shrimp", "Squid", "CrabMeats", "GreenPepper", "Onion", "Mushroom", "Pineapple", "BlackOlives"));
        ArrayList<String> selectedToppingsArray = new ArrayList<>();

        // Find ListViews
        allToppingsListView = findViewById(R.id.allToppings);
        selectedToppingsListView = findViewById(R.id.selectedToppings);

        // Create adapters for ListViews
        allToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, allToppingsArray);
        selectedToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, selectedToppingsArray);

        // Set adapters to ListViews
        allToppingsListView.setAdapter(allToppingsAdapter);
        selectedToppingsListView.setAdapter(selectedToppingsAdapter);

        // Find the RadioButtons
        RadioButton tomatoButton = findViewById(R.id.tomatoButton);
        RadioButton alfredoButton = findViewById(R.id.alfredoButton);

        // Handle click events
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

        // Find buttons
        Button addButton = findViewById(R.id.addButton);
        Button removeButton = findViewById(R.id.removeButton);
        Button orderButton = findViewById(R.id.orderButton);

        // Set click listeners for buttons
        addButton.setOnClickListener(v -> addTopping());
        removeButton.setOnClickListener(v -> removeTopping());
        orderButton.setOnClickListener(v -> placeOrder());
    }

    private void addTopping() {
        if (selectedToppingsAdapter.getCount() >= MAX_TOPPINGS) {
            showAlert("Maximum toppings reached!");
            return;
        }
        // Add selected topping from allToppings to selectedToppings
        int position = allToppingsListView.getCheckedItemPosition();
        if (position != ListView.INVALID_POSITION) {
            String topping = allToppingsAdapter.getItem(position);
            selectedToppingsAdapter.add(topping);
            allToppingsAdapter.remove(topping);
        }
    }

    private void removeTopping() {
        if (selectedToppingsAdapter.getCount() <= MIN_TOPPINGS) {
            showAlert("At least 3 toppings required!");
            return;
        }
        // Add selected topping from selectedToppings to allToppings
        int position = selectedToppingsListView.getCheckedItemPosition();
        if (position != ListView.INVALID_POSITION) {
            String topping = selectedToppingsAdapter.getItem(position);
            allToppingsAdapter.add(topping);
            selectedToppingsAdapter.remove(topping);
        }
    }

    private void placeOrder() {
        // Implement your logic to place the order
        // This is just a placeholder, you can customize it as per your requirement
        Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT).show();
    }

    private void showAlert(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(message)
                .setPositiveButton("OK", (dialog, id) -> {
                    // User clicked OK button
                });
        // Create the AlertDialog object
        AlertDialog dialog = alert.create();
        dialog.show();
    }

}

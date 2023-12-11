package com.example.ru_pizza_app;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.ComponentActivity;

import java.util.ArrayList;
import java.util.List;

public class StoreOrdersActivity extends ComponentActivity {
    private StoreOrders storeOrders = StoreOrders.getInstance();

    private Spinner orderNumberSpinner;
    private EditText subtotalField;
    private ListView pizzaList;
    private ArrayAdapter<Pizza> pizzaArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storeorders);

        orderNumberSpinner = findViewById(R.id.orderNumberSpinner);
        subtotalField = findViewById(R.id.subtotalField);
        pizzaList = findViewById(R.id.pizzaList);

        List<Integer> orderNumbers = new ArrayList<Integer>();
        for (Order order : storeOrders.getOrders()) {
            orderNumbers.add(order.getOrderNumber());
        }
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumbers);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumberSpinner.setAdapter(spinnerAdapter);

        pizzaArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        pizzaList.setAdapter(pizzaArrayAdapter);

        orderNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateOrderDetails();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        Button removeButton = findViewById(R.id.removePizzaButton);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeOrder();
            }
        });
    }

    private void updateOrderDetails() {
        int selectedOrderNumber = (int) orderNumberSpinner.getSelectedItem();

        Order selectedOrder = null;
        for (Order order : storeOrders.getOrders()) {
            if (order.getOrderNumber() == selectedOrderNumber) {
                selectedOrder = order;
                break;
            }
        }
        if (selectedOrder != null) {
            double orderTotal = selectedOrder.getTotalOrderPrice();
            subtotalField.setText(String.format("%.2f", orderTotal));

            List<Pizza> pizzas = selectedOrder.getPizzas();
            pizzaArrayAdapter.clear();
            pizzaArrayAdapter.addAll(pizzas);
        }
    }

    public void removeOrder() {
        if (orderNumberSpinner.getSelectedItem() == null) {
            showAlert("No Order Selected");
            return;
        }
        int selectedOrderNumber = (int) orderNumberSpinner.getSelectedItem();

        Order selectedOrder = null;
        for (Order order : storeOrders.getOrders()) {
            if (order.getOrderNumber() == selectedOrderNumber) {
                selectedOrder = order;
                break;
            }
        }
        if (selectedOrder != null) {
            storeOrders.getOrders().remove(selectedOrder);
        }

        updateOrderNumbers();

        clearOrderDetails();
    }

    private void updateOrderNumbers() {
        List<Integer> orderNumbers = new ArrayList<>();
        for (Order order : storeOrders.getOrders()) {
            orderNumbers.add(order.getOrderNumber());
        }
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, orderNumbers);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumberSpinner.setAdapter(spinnerAdapter);
    }

    private void clearOrderDetails() {
        subtotalField.setText("");
        pizzaArrayAdapter.clear();
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

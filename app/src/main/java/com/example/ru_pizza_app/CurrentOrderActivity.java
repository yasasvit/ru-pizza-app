package com.example.ru_pizza_app;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrderActivity extends ComponentActivity {
    private StoreOrders storeOrders = StoreOrders.getInstance();
    private PizzaOrderService pizzaOrderService = PizzaOrderService.getInstance();
    private ListView orderListView;
    private ArrayAdapter<Pizza> pizzaArrayAdapter;
    private TextView subtotalField;
    private TextView salesTaxField;
    private TextView totalField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentorder);

        Button removeButton = findViewById(R.id.removePizzaButton);
        Button placeOrderButton = findViewById(R.id.button9);

        orderListView = findViewById(R.id.orderList);
        subtotalField = findViewById(R.id.subtotalField);
        salesTaxField = findViewById(R.id.salesTaxField);
        totalField = findViewById(R.id.orderTotalField);

        pizzaArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, pizzaOrderService.getCurrentOrder().getPizzas());
        orderListView.setAdapter(pizzaArrayAdapter);
        updatePriceFields();
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePizza();
            }
        });

        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    private void updatePriceFields() {
        double currentTotalPrice = pizzaOrderService.getCurrentOrder().getTotalOrderPrice();
        double njTax = 0.06625;
        double salesTax = njTax * currentTotalPrice;
        double totalPriceWithTax = currentTotalPrice + salesTax;

        subtotalField.setText(String.format("%.2f", currentTotalPrice));
        salesTaxField.setText(String.format("%.2f", salesTax));
        totalField.setText(String.format("%.2f", totalPriceWithTax));
    }
    private void updateOrderListView() {
        List<Pizza> pizzas = pizzaOrderService.getCurrentOrder().getPizzas();
        List<String> pizzaStrings = new ArrayList<>();

        for (Pizza pizza : pizzas) {
            pizzaStrings.add(pizza.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzaStrings);
        orderListView.setAdapter(adapter);
    }

    private void removePizza() {
        int selectedIndex = orderListView.getCheckedItemPosition();

        if (selectedIndex >= 0) {
            pizzaOrderService.getCurrentOrder().getPizzas().remove(selectedIndex);

            updateOrderListView();
            updatePriceFields();
        } else {
            showAlert("Pizza needs to be selected in order to be removed.");
        }
    }

    private void placeOrder() {
        Order currentOrder = pizzaOrderService.getCurrentOrder();

        if (currentOrder.getPizzas().isEmpty()) {
            showAlert("No pizzas in the order.");
            return;
        }

        storeOrders.addOrder(currentOrder);

        pizzaOrderService.clearCurrentOrder();

        updatePriceFields();
        updateOrderListView();
        Toast.makeText(this, "Order placed!", Toast.LENGTH_SHORT).show();

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

package com.example.ru_pizza_app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.ComponentActivity;

public class CurrentOrderActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentorder);

        // Assuming you have a list of orders, replace with your data
        String[] orders = {"Order 1", "Order 2", "Order 3"};

//        ListView listView = findViewById(R.id.listView);
//        ArrayAdapter<String> ordersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, orders);
//        listView.setAdapter(ordersAdapter);

        Button removeButton = findViewById(R.id.button6);
        Button placeOrderButton = findViewById(R.id.button9);

        // Add click listeners for your buttons here
    }
}

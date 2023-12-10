package com.example.ru_pizza_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.ComponentActivity;

public class MainActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);

        Button specialtyPizzasButton = findViewById(R.id.specialtyPizzasButton);
        Button byoButton = findViewById(R.id.byoButton);
        Button currentOrderButton = findViewById(R.id.currentOrderButton);
        Button storeOrdersButton = findViewById(R.id.storeOrdersButton);

        specialtyPizzasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Button s clicked");
                startActivity(new Intent(MainActivity.this, SpecialtyPizzasActivity.class));
            }
        });

        byoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("BYO CLicked");
                startActivity(new Intent(MainActivity.this, BuildYourOwnActivity.class));
            }
        });

        currentOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CurrentOrderActivity.class));
            }
        });

        storeOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StoreOrdersActivity.class));
            }
        });
    }
}

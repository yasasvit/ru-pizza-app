package com.example.ru_pizza_app;

import android.os.Bundle;

import androidx.activity.ComponentActivity;
import androidx.activity.compose.setContent;
import androidx.compose.foundation.layout.fillMaxSize;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.Surface;
import androidx.compose.material3.Text;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.tooling.preview.Preview;

import com.example.ru_pizza_app.ui.theme.RupizzaappTheme;

public class MainActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent(() -> {
            RupizzaappTheme.INSTANCE.applyTheme(
                    this,
                    new Surface.Modifier[]{Modifier.fillMaxSize()},
                    MaterialTheme.INSTANCE.getColorScheme().background,
                    () -> {
                        Greeting("Android");
                        return null;
                    }
            );
        });
    }

    @Composable
    public static void Greeting(String name, Modifier modifier) {
        Text.INSTANCE.setText("Hello " + name + "!", modifier);
    }

    @Preview(showBackground = true)
    @Composable
    public static void GreetingPreview() {
        RupizzaappTheme.INSTANCE.applyTheme(
                null,
                new Surface.Modifier[]{},
                null,
                () -> {
                    Greeting("Android", Modifier.DEFAULT);
                    return null;
                }
        );
    }
}

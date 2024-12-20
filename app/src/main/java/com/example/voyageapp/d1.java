package com.example.voyageapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class d1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_d1);
        Intent intent = getIntent();
        String categoryName = getIntent().getStringExtra("category");
        double latitude = getIntent().getDoubleExtra("latitude", 0.0);
        double longitude = getIntent().getDoubleExtra("longitude", 0.0);

        // Utilisez ces valeurs pour configurer votre interface ou charger des données.
        TextView categoryTitle = findViewById(R.id.categoryTitle);
        categoryTitle.setText(categoryName);

        // Exemple d'affichage des coordonnées
        TextView coordinates = findViewById(R.id.coordinates);
        coordinates.setText("Lat: " + latitude + ", Long: " + longitude);
    }
}
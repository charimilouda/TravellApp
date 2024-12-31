package com.example.voyageapp;

import static com.example.voyageapp.R.drawable.progress_inactive;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class pages extends AppCompatActivity{
    private LinearLayout screen1, screen2, screen3;
    private Button btnNext;
    private int currentScreen = 1; // Indicateur d'écran actuel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);

        // Récupérer les éléments de l'interface
        screen1 = findViewById(R.id.screen1);
        screen2 = findViewById(R.id.screen2);
        screen3 = findViewById(R.id.screen3);
        btnNext = findViewById(R.id.btn_next);
        // Récupérer les éléments de la barre de progression
        View progress1 = findViewById(R.id.progress1);
        View progress2 = findViewById(R.id.progress2);
        View progress3 = findViewById(R.id.progress3);


        // Bouton Next
        btnNext.setOnClickListener(v -> {
            if (currentScreen == 1) {
                // Mise à jour de la barre de progression
                screen1.setVisibility(View.GONE);
                screen2.setVisibility(View.VISIBLE);
                currentScreen++;
                btnNext.setText("Suivant"); // Change le texte du bouton
                progress2.setBackgroundResource(R.drawable.progress_active);
                progress1.setBackgroundResource(progress_inactive);

            } else if (currentScreen == 2) {
                // Mise à jour de la barre de progression
                progress2.setBackgroundColor(Color.parseColor("#0D6EFD"));
                screen2.setVisibility(View.GONE);
                screen3.setVisibility(View.VISIBLE);
                currentScreen++;
                progress3.setBackgroundResource(R.drawable.progress_active);
                progress2.setBackgroundResource(R.drawable.progress_inactive);
            } else if (currentScreen == 3) {
                // Naviguer vers la page de connexion
                Intent intent = new Intent(pages.this, Login.class);
                startActivity(intent);
                finish(); // Ferme cette activité


            }
        });

    }

}


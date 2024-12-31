package com.example.voyageapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voyageapp.R;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Délai avant de passer à la page suivante
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Passe à la page de connexion après 2 secondes
                Intent intent = new Intent(MainActivity.this, pages.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // 2 secondes
    }
}

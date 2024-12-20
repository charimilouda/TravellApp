package com.example.voyageapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.voyageapp.model.PlacesList;
import com.example.voyageapp.model.SuggestedPlaces;

public class SeeAll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_see_all);
        ImageView iconLeft = findViewById(R.id.icon_left);
        GridLayout itemsContainer = findViewById(R.id.items_container);
        PlacesList pl=new PlacesList();
        for (SuggestedPlaces place : pl.getSuggestedPlaces()) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.suggested_row_item, itemsContainer, false);

            // Remplir les données
            ImageView placeImage = itemView.findViewById(R.id.place_image);
            TextView placeName = itemView.findViewById(R.id.place_name);
            TextView countryName = itemView.findViewById(R.id.country_name);
            TextView categorie = itemView.findViewById(R.id.categorie);

            placeImage.setImageResource(place.getImageUrl());
            placeName.setText(place.getPlaceName());
            countryName.setText(place.getCountryName());
            categorie.setText(place.getCategorie());

            // Ajouter l'item au GridLayout
            itemsContainer.addView(itemView);

            //ce listner je pense que je dis le mettre en dehors de la boucle for
            // Listener pour l'ImageView
            iconLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Rediriger vers l'Activity VerifyOTPActivity
                    Intent intent = new Intent(SeeAll.this, Home.class);
                    startActivity(intent);
                }
            });
        }

    }
}
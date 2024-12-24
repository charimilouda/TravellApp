package com.example.voyageapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.voyageapp.data.model.Restaurant;
import com.example.voyageapp.ui.resultats.CafeActivity;
import com.example.voyageapp.ui.resultats.HotelActivity;
import com.example.voyageapp.ui.resultats.MuseumActivity;
import com.example.voyageapp.ui.resultats.ParkActivity;
import com.example.voyageapp.ui.resultats.RestaurantActivity;
import com.example.voyageapp.ui.resultats.ShoppingActivity;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView D1,D2,D3,D4,D5,D6 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        D1=(CardView) findViewById(R.id.d1);
        D2=(CardView) findViewById(R.id.d2);
        D3=(CardView) findViewById(R.id.d3);
        D4=(CardView) findViewById(R.id.d4);
        D5=(CardView) findViewById(R.id.d5);
        D6=(CardView) findViewById(R.id.d6);



        D1.setOnClickListener((View.OnClickListener) this);
        D2.setOnClickListener((View.OnClickListener) this);
        D3.setOnClickListener((View.OnClickListener) this);
        D4.setOnClickListener((View.OnClickListener) this);
        D5.setOnClickListener((View.OnClickListener) this);
        D6.setOnClickListener((View.OnClickListener) this);

        ImageView homeIcon = findViewById(R.id.icon_home);
        ImageView homeIconClic = findViewById(R.id.icon_home2);
        ImageView favoriIcon = findViewById(R.id.icon_favori);
        ImageView favoriIconClic = findViewById(R.id.icon_favori2);
        ImageView profileIcon = findViewById(R.id.icon_profile);
        ImageView profileIconClic = findViewById(R.id.icon_profile2);


        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0);
        double longitude = intent.getDoubleExtra("longitude", 0);

        // Utilisez latitude et longitude ici
        Toast.makeText(this, "Lat: " + latitude + ", Lon: " + longitude, Toast.LENGTH_SHORT).show();

        // Listener pour l'ImageView
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeIcon.setVisibility(View.GONE);
                homeIconClic.setVisibility(View.VISIBLE);
                favoriIcon.setVisibility(View.VISIBLE);
                favoriIconClic.setVisibility(View.GONE);
                profileIcon.setVisibility(View.VISIBLE);
                profileIconClic.setVisibility(View.GONE);
                // Rediriger vers l'Activity home
                Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // Listener pour l'ImageView
        favoriIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriIcon.setVisibility(View.GONE);
                favoriIconClic.setVisibility(View.VISIBLE);
                homeIcon.setVisibility(View.VISIBLE);
                homeIconClic.setVisibility(View.GONE);
                profileIcon.setVisibility(View.VISIBLE);
                profileIconClic.setVisibility(View.GONE);
                // Rediriger vers l'Activity favorites
                Intent intent = new Intent(CategoryActivity.this, Favori.class);
                startActivity(intent);
            }
        });
        // Listener pour l'ImageView
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileIcon.setVisibility(View.GONE);
                profileIconClic.setVisibility(View.VISIBLE);
                favoriIcon.setVisibility(View.VISIBLE);
                favoriIconClic.setVisibility(View.GONE);
                homeIcon.setVisibility(View.VISIBLE);
                homeIconClic.setVisibility(View.GONE);
                // Rediriger vers l'Activity Profile
                Intent intent = new Intent(CategoryActivity.this, Profil.class);
                startActivity(intent);
            }
        });



    }
    @Override
    public void onClick(View v){
        Intent i;
        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0);
        double longitude = intent.getDoubleExtra("longitude", 0);
        String category = "";
        if (v.getId() == R.id.d1) {
            i = new Intent(this, RestaurantActivity.class);
            category = "restaurant";
        } else if (v.getId() == R.id.d2) {
            i = new Intent(this, HotelActivity.class);
            category = "hotel";
        } else if (v.getId() == R.id.d3) {
            i = new Intent(this, MuseumActivity.class);
            category = "shopping";
        } else if (v.getId() == R.id.d4) {
            i = new Intent(this, ParkActivity.class);
            category = "park";
        } else if (v.getId() == R.id.d5) {
            i = new Intent(this, ShoppingActivity.class);
            category = "museum";
        } else if (v.getId() == R.id.d6) {
            i = new Intent(this, CafeActivity.class);
            category = "cafe";
        }
        else{
            return; // Aucun ID correspondant, on ne fait rien
        }
        // Ajouter latitude, longitude et catégorie à l'Intent
        i.putExtra("latitude", latitude);
        i.putExtra("longitude", longitude);
        i.putExtra("category", category);

        startActivity(i);
    }
    /*
    Utilisation dans l'activité suivante :
Dans l'activité cible (d1, d2, etc.), vous pouvez récupérer ces valeurs de cette façon :
    * Intent intent = getIntent();
double latitude = intent.getDoubleExtra("latitude", 0);
double longitude = intent.getDoubleExtra("longitude", 0);
String category = intent.getStringExtra("category");

// Utilisation des données
Toast.makeText(this, "Catégorie: " + category + ", Lat: " + latitude + ", Lon: " + longitude, Toast.LENGTH_SHORT).show();
*/
}
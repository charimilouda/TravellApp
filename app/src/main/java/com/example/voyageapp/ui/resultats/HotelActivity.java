package com.example.voyageapp.ui.resultats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import com.example.voyageapp.adapter.HotelAdapter;
import com.example.voyageapp.databinding.ActivityHotelBinding;
import com.example.voyageapp.viewModel.LieuViewModel;
import com.example.voyageapp.viewModel.injection.HotelViewModelFactory;

public class HotelActivity extends AppCompatActivity {
    private ActivityHotelBinding binding;
    private Intent intent;
    private double latitude = 34.689404;
    private double longitude =-1.912823;
    private String category = "hotel";
    private LieuViewModel hotelViewModel;
    private HotelAdapter adapter;
    private final String apiKey = "e4c34a66df174a229066590ee94189e5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // initialisation des resources

        intent = getIntent();
        adapter = new HotelAdapter(new ArrayList<>());
        binding.listItem.setLayoutManager(new LinearLayoutManager(this));
        binding.listItem.setAdapter(adapter);

        // creation du ViewModel via ViewModelProvides

        HotelViewModelFactory factory = new HotelViewModelFactory(apiKey);
        hotelViewModel = new ViewModelProvider(this,factory).get(LieuViewModel.class);


        // recuperation des extraData

        handleIntentData();


        // Observer Les Données
        hotelViewModel.getCategorie().observe(this, categorie -> {
            if (categorie != null) {
                hotelViewModel.getFilter().observe(this, filter -> {
                    if (filter != null) {
                        // Recharger les données avec les nouvelles valeurs
                        hotelViewModel.getHotelsLiveData(categorie, filter).observe(this, hotels -> {
                            if (hotels != null) {
                                binding.progressBar.setVisibility(View.GONE);
                                adapter.updateData(hotels);
                            } else {
                                Toast.makeText(this, "Aucun résultat trouvé", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });


        binding.backBtn.setOnClickListener(view -> {
            finish();
        });


    }

    private void handleIntentData() {
        this.latitude = intent.getDoubleExtra("latitude", 0);
        this.longitude = intent.getDoubleExtra("longitude", 0);
        this.category = intent.getStringExtra("category");

        if (latitude == 0 || longitude == 0 || category == null) {
            Toast.makeText(this, "Données invalides. Veuillez réessayer.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }else {
            hotelViewModel.setCategorie(hotelViewModel.searchCategorie(category));
            hotelViewModel.setFilter("circle:" + longitude + "," + latitude + "," + 10000);
        }


    }


}
package com.example.voyageapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class favoris extends AppCompatActivity {

    private GridLayout favoritesList;
    private DatabaseReference databaseReference;
    private String currentUserId;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoris);
        ImageView homeIcon = findViewById(R.id.icon_home);
        ImageView homeIconClic = findViewById(R.id.icon_home2);
        ImageView favoriIcon = findViewById(R.id.icon_favori);
        ImageView favoriIconClic = findViewById(R.id.icon_favori2);
        ImageView profileIcon = findViewById(R.id.icon_profile);
        ImageView profileIconClic = findViewById(R.id.icon_profile2);


        favoritesList = findViewById(R.id.favorites_list);

        // Obtenir l'UID de l'utilisateur connecté
        currentUserId = "1";//currentUser.getUid();

        // Référence à la base de données Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Favoris").child(currentUserId).child("hotel");

        // Charger les données
        loadFavorites();

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Rediriger vers l'Activity home
                Intent intent = new Intent(favoris.this, Home.class);
                startActivity(intent);
            }
        });

        // Listener pour l'ImageView
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Rediriger vers l'Activity Profile
                Intent intent = new Intent(favoris.this, Profil.class);
                startActivity(intent);
            }
        });
    }

    private void loadFavorites() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                favoritesList.removeAllViews(); // Efface les anciennes vues si nécessaire

                if (snapshot.exists()) {
                    for (DataSnapshot hotelSnapshot : snapshot.getChildren()) {
                        // Récupérer les données de chaque favori
                        FavorisClass favori = hotelSnapshot.getValue(FavorisClass.class);
                        // Ajouter à l'UI
                        if (favori != null) {
                            addFavoriteCard(favori, hotelSnapshot.getKey());
                        }
                    }
                } else {
                    Toast.makeText(favoris.this, "Aucun favori trouvé", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(favoris.this, "Erreur : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addFavoriteCard(FavorisClass favori, String key) {
        // Charger la vue de la carte à partir de favoris_card.xml
        View cardView = LayoutInflater.from(this).inflate(R.layout.favoris_card, favoritesList, false);

        // Associer les éléments de la carte
        ImageView imgPlace = cardView.findViewById(R.id.img_place);
        TextView tvPlaceName = cardView.findViewById(R.id.tv_place_name);
        TextView tvPlaceLocation = cardView.findViewById(R.id.tv_place_location1);
        ImageButton imgTrash = cardView.findViewById(R.id.img_trash);


        // Charger l'image avec Glide
        Glide.with(this)
                .load(favori.getImageUrl())
                .placeholder(R.drawable.house4) // Placeholder si l'image est vide
                .into(imgPlace);

        // Définir les textes
        tvPlaceName.setText(favori.getName());
        tvPlaceLocation.setText(favori.getAddress());

        // Configurer l'action de suppression
        imgTrash.setOnClickListener(v -> {
            databaseReference.child(key).removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(favoris.this, "Favori supprimé avec succès", Toast.LENGTH_SHORT).show();
                    favoritesList.removeView(cardView); // Supprimer la carte de la vue
                } else {
                    Toast.makeText(favoris.this, "Erreur lors de la suppression", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Ajouter la carte à la liste
        favoritesList.addView(cardView);
    }
}

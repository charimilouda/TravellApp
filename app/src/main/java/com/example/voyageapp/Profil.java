package com.example.voyageapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class Profil extends AppCompatActivity {

    private TextView textViewName, textViewEmail;
    private ImageView imageViewProfile;
    private DatabaseReference userRef;
    ImageButton buttonReturn ;
    FirebaseAuth mAuth;
    FirebaseUser currentUser ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        buttonReturn = findViewById(R.id.buttonReturn);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            // Si aucun utilisateur n'est connecté, rediriger vers l'écran de connexion
         //   startActivity(new Intent(Profil.this, LoginActivity.class));
            finish();
        } else {
            // Référence Firebase Database pour l'utilisateur connecté
            String userId = currentUser.getUid();
            userRef = FirebaseDatabase.getInstance().getReference("utilisateur").child(userId);

            // Récupération des données utilisateur
            fetchUserData();
        }

        fetchUserData();

        setupButtonListeners();
    }

    private void fetchUserData() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    String prenom = dataSnapshot.child("prenom").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    UserClass user = dataSnapshot.getValue(UserClass.class);

                    textViewName.setText(prenom);
                    textViewEmail.setText(email);

                    if (user.getImageUrl() != null && !user.getImageUrl().isEmpty()) {
                        Picasso.get().load("file://" + user.getImageUrl()).transform(new CropCircleTransformation()).into(imageViewProfile);

                    } else {
                        imageViewProfile.setImageResource(R.drawable.femme);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Profil.this, "Erreur lors du chargement des données", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupButtonListeners() {
        ImageButton buttonEdit = findViewById(R.id.buttonEdit);
        LinearLayout profiledetails = findViewById(R.id.profiledetailslayout);
        LinearLayout placesfavoris = findViewById(R.id.placesfavoris);

        buttonReturn.setOnClickListener(view -> finish());
        buttonEdit.setOnClickListener(view -> {
            Intent intent = new Intent(Profil.this, editprofil.class);
            startActivity(intent);
        });

        profiledetails.setOnClickListener(view -> {
            Intent intent = new Intent(Profil.this, profildetails.class);
            startActivity(intent);
        });

        placesfavoris.setOnClickListener(view -> {
            Intent intent = new Intent(Profil.this, favoris.class);
            startActivity(intent);
        });
    }
}
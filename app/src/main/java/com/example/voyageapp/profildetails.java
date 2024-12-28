package com.example.voyageapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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


public class profildetails extends AppCompatActivity {

    private TextView editTextNom, editTextPrenom,prenomprofil;
    private EditText editTextEmail, editTextEmplacement, editTextNumero;
    private ImageView imageViewProfile;

      FirebaseAuth mAuth = FirebaseAuth.getInstance();
      FirebaseUser currentUser = mAuth.getCurrentUser();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_details);

        // Initialisation des champs
        ImageButton buttonEdit = findViewById(R.id.buttonEdit);
        ImageButton buttonReturn = findViewById(R.id.buttonReturn);
        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmplacement = findViewById(R.id.editTextEmplacement);
        editTextNumero = findViewById(R.id.editTextNumero);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        prenomprofil=findViewById(R.id.prenomprofil);


        // Charger les données utilisateur
        loadUserData();

        // Retour à l'activité précédente
        buttonReturn.setOnClickListener(view -> finish());

        // Aller à l'activité de modification
        buttonEdit.setOnClickListener(view -> {
            Intent intent = new Intent(profildetails.this, editprofil.class);
            //intent.putExtra("userId", "1");// mnb3d ndwz userId variable
            startActivity(intent);
        });
    }

    private void loadUserData() {
        // Référence Firebase pour l'utilisateur
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("utilisateur").child(currentUser.getUid());//mnb3d thyd

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Obtenir les données utilisateur
                    UserClass user = dataSnapshot.getValue(UserClass.class);
                    if (user != null) {
                        // Mettre à jour l'interface utilisateur
                        editTextNom.setText(user.getNom());
                        editTextPrenom.setText(user.getPrenom());
                        editTextEmail.setText(user.getEmail());
                        editTextEmplacement.setText(user.getEmplacement());
                        editTextNumero.setText(user.getNumerotele());
                        prenomprofil.setText(user.getPrenom());
                        // Charger l'image depuis la mémoire interne
                        String imageUrl = user.getImageUrl();
                        if (imageUrl != null && !imageUrl.isEmpty()) {
                            Picasso.get().load("file://" + user.getImageUrl()).transform(new CropCircleTransformation()).into(imageViewProfile);
                        } else {
                            imageViewProfile.setImageResource(R.drawable.femme);
                            // Image par défaut
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(profildetails.this, "Erreur de chargement : " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadUserData();
    }
}
package com.example.voyageapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    private EditText emailInput, passwordInput, confirmPasswordInput;
    private Button signUpButton;
    private TextView signin;
    private ImageView buttonReturn;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        // Initialisation des vues
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        confirmPasswordInput = findViewById(R.id.confirm_password_input);
        signUpButton = findViewById(R.id.sign_up_button);
        signin = findViewById(R.id.sign_in);
        buttonReturn = findViewById(R.id.buttonReturn);

        // Initialisation de Firebase Auth et Database
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        // Action pour le bouton d'inscription (ici vous pouvez ajouter la logique d'inscription)
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                String confirmPassword = confirmPasswordInput.getText().toString().trim();

                // Validation des champs
                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(signup.this, "Tous les champs sont requis", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(signup.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Créer un compte avec Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            String userId = firebaseUser.getUid();

                            // Création d'une instance UserClass
                            UserClass newUser = new UserClass(
                                    email,
                                    "",  // Emplacement (vide pour l'instant)
                                    "",  // ImageUrl (vide pour l'instant)
                                    password,
                                    userId,
                                    "",  // Prenom (vide pour l'instant)
                                    "",  // Nom (vide pour l'instant)
                                    ""   // Numerotele (vide pour l'instant)
                            );

                            // Enregistrer les informations utilisateur dans Firebase Realtime Database
                            mDatabase.child("utilisateur").child(userId).setValue(newUser).addOnCompleteListener(dbTask -> {
                                if (dbTask.isSuccessful()) {
                                    Toast.makeText(signup.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                                    // Redirection vers la page de connexion ou d'accueil
                                    Intent intent = new Intent(signup.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(signup.this, "Échec de l'enregistrement des données", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(signup.this, "Échec de l'inscription : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

                // Logique d'inscription ici (ex : validation des champs, appel à l'API, etc.)
        });

        // Action pour le bouton "Se connecter" : redirection vers la page de connexion
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers la page de connexion
                Intent intent = new Intent(signup.this, Login.class);
                startActivity(intent);
            }
        });
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers la page de connexion
                Intent intent = new Intent(signup.this, Login.class);
                startActivity(intent);
            }
        });

    }
}


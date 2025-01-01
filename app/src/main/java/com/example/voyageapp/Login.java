package com.example.voyageapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voyageapp.ForgottenPassword;
import com.example.voyageapp.Home;
import com.example.voyageapp.R;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;


import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private EditText emailInput, passwordInput;
    private Button signInButton;
    private TextView signup;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 9001; // Code de demande pour le résultat de la connexion
    private TextView forgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        // Initialisation de FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        // Initialisation des vues
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        signInButton = findViewById(R.id.sign_in_button);
        signup = findViewById(R.id.sign_up);
        forgotpass=findViewById(R.id.forgotpass);
        // Gestion du clic sur le bouton "Se connecter"
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    // Connexion à Firebase Authentication
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            if (user != null) {
                                // Connexion réussie
                                Toast.makeText(Login.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                // Redirection vers la page d'accueil ou une autre activité
                                Intent intent = new Intent(Login.this, Home.class);
                                intent.putExtra("userId",user.getUid());
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            // En cas d'échec de la connexion
                            Toast.makeText(Login.this, "Échec de la connexion : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(Login.this, "Veuillez entrer l'email et le mot de passe", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, signup.class);
            startActivity(intent);
        });
        forgotpass.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, ForgottenPassword.class);
            startActivity(intent);
        });








    }
}
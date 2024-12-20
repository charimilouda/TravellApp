package com.example.voyageapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class ForgottenPassword extends AppCompatActivity {
    private FirebaseAuth mAuth; // Instance de FirebaseAuth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);


        final EditText inputEmail = findViewById(R.id.emailField);
        TextView renvoyer = findViewById(R.id.resend);
        TextView renvoyerText = findViewById(R.id.textResend);
        Button buttonSentOTP = findViewById(R.id.buttonGetOTP);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        renvoyer.setVisibility(View.INVISIBLE);
        renvoyerText.setVisibility(View.INVISIBLE);
        ImageButton iconLeft = findViewById(R.id.icon_left);
        // Définir le listener sur le bouton et le TextView
        //buttonSentOTP.setOnClickListener(sendEmailListener);
        //renvoyer.setOnClickListener(sendEmailListener);
        // Initialiser FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Fonction d'envoi de l'email
        View.OnClickListener sendEmailListener = view -> {
            String email = inputEmail.getText().toString().trim();

            if (email.isEmpty()) {
                Toast.makeText(ForgottenPassword.this, "Entrer l'Email", Toast.LENGTH_SHORT).show();
                return;
            }

            // Afficher la ProgressBar et cacher le bouton/texte
            progressBar.setVisibility(View.VISIBLE);
            buttonSentOTP.setVisibility(View.INVISIBLE);


            // Envoyer l'email de réinitialisation de mot de passe
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        buttonSentOTP.setVisibility(View.VISIBLE);
                        renvoyer.setVisibility(View.VISIBLE);
                        renvoyerText.setVisibility(View.VISIBLE);

                        if (task.isSuccessful()) {
                            Toast.makeText(ForgottenPassword.this, "E-mail envoyé ! Vérifiez votre boîte de réception.", Toast.LENGTH_SHORT).show();
                            // Facultatif : Redirection vers l'écran de connexion après l'envoi
                            //Intent intent = new Intent(SendOTPActivity.this, Login.class);
                            //(intent);
                            //finish();
                        } else {
                            Exception exception = task.getException();
                            if (exception instanceof FirebaseAuthInvalidUserException) {
                                // Gérer le cas où l'utilisateur n'existe pas
                                Toast.makeText(ForgottenPassword.this, "Aucun compte associé à cet e-mail.", Toast.LENGTH_LONG).show();
                            } else if (exception != null) {
                                // Gérer d'autres erreurs
                                Toast.makeText(ForgottenPassword.this, "Erreur : " + exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        };

        // Définir le listener sur le bouton et le TextView
        buttonSentOTP.setOnClickListener(sendEmailListener);
        renvoyer.setOnClickListener(sendEmailListener);



        iconLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Rediriger vers l'Activity VerifyOTPActivity
                Intent intent = new Intent(ForgottenPassword.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
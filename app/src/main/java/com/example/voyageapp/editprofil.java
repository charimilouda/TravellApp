package com.example.voyageapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class editprofil extends AppCompatActivity {

    private EditText editTextNom, editTextPrenom, editTextEmplacement, editTextNumero, editTextEmail;
    private ImageView imageViewProfile;
    private TextView textChangePhoto, prenomprofil;
    private DatabaseReference userRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser = mAuth.getCurrentUser();
    private UserClass user;
    private String localImagePath;
    ImageButton buttonReturn;
    TextView buttonSave;

    private ActivityResultLauncher<Intent> imagePickerLauncher;
    private ActivityResultLauncher<Intent> cameraLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofil);

        String userId =currentUser.getUid();;
        userRef = FirebaseDatabase.getInstance().getReference("utilisateur").child(userId);

        editTextNom = findViewById(R.id.editTextNom);
        editTextPrenom = findViewById(R.id.editTextPrenom);
        editTextEmplacement = findViewById(R.id.editTextEmplacement);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextEmail = findViewById(R.id.editTextEmail);
        imageViewProfile = findViewById(R.id.imageViewProfile);
        textChangePhoto = findViewById(R.id.textChangePhoto);
        prenomprofil = findViewById(R.id.prenomprofil);
        buttonReturn = findViewById(R.id.buttonReturn);
        buttonSave = findViewById(R.id.buttonSave);

        loadUserData();
        buttonReturn.setOnClickListener(view -> finish());
        buttonSave.setOnClickListener(view -> saveUserData());
        imageViewProfile.setOnClickListener(view -> changeProfileImage());
        textChangePhoto.setOnClickListener(view -> changeProfileImage());

        registerActivityResultLaunchers();
    }

    private void loadUserData() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    user = snapshot.getValue(UserClass.class);
                    if (user != null) {
                        editTextNom.setText(user.getNom());
                        editTextPrenom.setText(user.getPrenom());
                        editTextEmplacement.setText(user.getEmplacement());
                        editTextNumero.setText(user.getNumerotele());
                        editTextEmail.setText(user.getEmail());
                        prenomprofil.setText(user.getPrenom());
                        if (user.getImageUrl() != null && !user.getImageUrl().isEmpty()) {
                            Picasso.get().load("file://" + user.getImageUrl()).into(imageViewProfile);
                        } else {
                            Picasso.get().load(R.drawable.femme).into(imageViewProfile);
                        }
                    }
                } else {
                    Toast.makeText(editprofil.this, "Les informations de l'utilisateur non trouvées!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(editprofil.this, "Erreur de chargement: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserData() {
        String updatedNom = editTextNom.getText().toString().trim();
        String updatedPrenom = editTextPrenom.getText().toString().trim();
        String updatedEmplacement = editTextEmplacement.getText().toString().trim();
        String updatedNumero = editTextNumero.getText().toString().trim();
        String updatedEmail = editTextEmail.getText().toString().trim();

        if (updatedNom.isEmpty() || updatedPrenom.isEmpty() || updatedEmplacement.isEmpty() || updatedNumero.isEmpty() || updatedEmail.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(updatedEmail).matches()) {
            Toast.makeText(this, "Le format de l'email n'est pas valide!", Toast.LENGTH_SHORT).show();
            return;
        }

        user = user == null ? new UserClass() : user;
        user.setNom(updatedNom);
        user.setPrenom(updatedPrenom);
        user.setEmplacement(updatedEmplacement);
        user.setNumerotele(updatedNumero);
        user.setEmail(updatedEmail);

        if (localImagePath != null) {
            user.setImageUrl(localImagePath);
        }

        userRef.setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(editprofil.this, "Profil mis à jour avec succès", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(editprofil.this, "Impossible de mettre à jour le profil", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void changeProfileImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Intent chooser = Intent.createChooser(galleryIntent, "Select Profile Image");
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{cameraIntent});

        imagePickerLauncher.launch(chooser);
    }

    private void registerActivityResultLaunchers() {
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bitmap photo;
                        Uri selectedImageUri = result.getData().getData();

                        if (selectedImageUri != null) {
                            try {
                                photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                            } catch (IOException e) {
                                Toast.makeText(this, "Erreur lors du chargement de l'image", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } else {
                            photo = (Bitmap) result.getData().getExtras().get("data");
                        }

                        localImagePath = saveImageToInternalStorage(photo);
                        Picasso.get().load("file://" + localImagePath).transform(new CropCircleTransformation()).into(imageViewProfile);
                    }
                }
        );
    }

    private String saveImageToInternalStorage(Bitmap photo) {
        File imageFile = new File(getFilesDir(), UUID.randomUUID().toString() + ".jpg");
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            return imageFile.getAbsolutePath();
        } catch (IOException e) {
            Log.e("ImageSaveError", "Error saving image: " + e.getMessage());
            return null;
        }
    }
}
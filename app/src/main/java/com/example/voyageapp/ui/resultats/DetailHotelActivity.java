package com.example.voyageapp.ui.resultats;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.tasks.Task;

import com.bumptech.glide.Glide;

import com.example.voyageapp.R;
import com.example.voyageapp.data.model.Favoris;
import com.example.voyageapp.databinding.ActivityDetailLieuBinding;
import com.example.voyageapp.viewModel.LieuViewModel;
import com.example.voyageapp.viewModel.injection.HotelViewModelFactory;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailHotelActivity extends AppCompatActivity {
    private ActivityDetailLieuBinding binding;




    private String type;
    private String name;
    private String address;
    private String country;
    private String city;
    private String website;
    private String imageUrl;
    private String altName; // Nom alternatif
    private String phone;// Téléphone
    private boolean wheelchairAccessible; // Accessibilité fauteuil roulant
    private int buildingLevels; //
    private String email; // Email
    private String openingHours; // Horaires d'ouverture
    private String description; // Description
    private boolean wheelchairToilets; // Toilettes accessibles
    private String twitterHandle; // Compte Twitter
    private String facebookPage;
    private String internationalName; // Nom international
    private boolean internetAccess; // Internet disponible
    private boolean airConditioning; // Climatisation
    private boolean outdoorSeating; // Places assises à l'extérieur
    private String wikidataLink;
    private boolean hasToilets; // Toilettes disponibles
    private String buildingType; // Type de bâtiment (retail)
    private int buildingHeight; // Hauteur du bâtiment
    private int startDate; // Année de construction
    private String commercialType; // Type commercial (mall)
    private String wikipediaLink;
    private double latitude;
    private double longitude;
    private String operator; // Opérateur
    private int heritageLevel; // Niveau de patrimoine
    private String heritageRef; // Référence patrimoine
    private int heritageInscriptionDate; // Date d'inscription
    private int buildingStartDate; // Année de construction
    private String wikimediaCommons;
    private String panoramaImageUrl; // URL de l'image panoramique

    private LieuViewModel lieuViewModel;
    private Favoris favoris = new Favoris();
    private final String apiKey = "e4c34a66df174a229066590ee94189e5";


    private DatabaseReference databaseref;
    // inittialisation FireBase DataBase








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailLieuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // initialisation de Firebase
        databaseref = FirebaseDatabase.getInstance().getReference("favoris").child("1");


        getIntentExtra();
        setDetailData();

    }

    public void getIntentExtra(){
        Intent intent = getIntent();

        this.type=intent.getStringExtra("type");
        this.name=intent.getStringExtra("name");
        this.longitude=intent.getDoubleExtra("longitude",0);
        this.latitude=intent.getDoubleExtra("latitude",0);
        this.address=intent.getStringExtra("address");
        this.country=intent.getStringExtra("country");
        this.city=intent.getStringExtra("city");
        this.imageUrl=intent.getStringExtra("imageUrl");
        this.altName=intent.getStringExtra("altName");
        this.website=intent.getStringExtra("website");
        this.phone=intent.getStringExtra("phone");
        this.email=intent.getStringExtra("email");
        this.twitterHandle=intent.getStringExtra("twitterHandle");
        this.facebookPage=intent.getStringExtra("facebookPage");
        this.internetAccess=intent.getBooleanExtra("internetAccess",false);
        this.internationalName=intent.getStringExtra("internationalName");
        this.airConditioning=intent.getBooleanExtra("airConditioning",false);
        this.wheelchairAccessible=intent.getBooleanExtra("wheelchairAccessible",false);
        this.wheelchairToilets=intent.getBooleanExtra("wheelchairToilets",false);
        this.hasToilets=intent.getBooleanExtra("hasToilets",false);
        this.buildingType=intent.getStringExtra("buildingType");
        this.buildingHeight=intent.getIntExtra("buildingHeight",0);
        this.startDate=intent.getIntExtra("startDate",0);
        this.openingHours=intent.getStringExtra("openingHours");
        this.operator=intent.getStringExtra("operator");
        this.buildingLevels=intent.getIntExtra("buildingLevels",0);
        this.buildingStartDate=intent.getIntExtra("buildingStartDate",0);
        this.heritageRef=intent.getStringExtra("heritageRef");
        this.heritageLevel=intent.getIntExtra("heritageLevel",0);
        this.wikidataLink=intent.getStringExtra("wikidataLink");
        this.wikipediaLink=intent.getStringExtra("wikipediaLink");
        this.heritageInscriptionDate=intent.getIntExtra("heritageInscriptionDate",0);
        this.wikimediaCommons=intent.getStringExtra("wikimediaCommons");
        this.panoramaImageUrl=intent.getStringExtra("panoramaImageUrl");
        this.outdoorSeating=intent.getBooleanExtra("outdoorSeating",false);
        this.commercialType=intent.getStringExtra("commercialType");
        this.description=intent.getStringExtra("description");

    }

    public void setDetailData() {
        // Affichage du nom principal ou alternatif
        if (name != null && !name.isEmpty()) {
            binding.lieuTitle.setText(type + ": " + name);
        } else if (altName != null && !altName.isEmpty()) {
            binding.lieuTitle.setText(type + ": " + altName);
        } else {
            binding.lieuTitle.setText("default Name");
        }

        // Affichage de l'adresse
        if (address != null && !address.isEmpty()) {
            binding.lieuAddress.setText(address);
        } else {
            binding.lieuAddress.setText(country + ", " + city);
        }

        // Affichage de l'image
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(this)
                    .load(imageUrl)
                    .into(binding.lieuImage);
        } else {
            binding.lieuImage.setImageResource(R.drawable.hotel_demo_img); // Image par défaut
        }



        binding.lieuDescription.setText(this.description);




        // Affichage du site web (avec gestion des clics)
        // initialisation du view model


        if (website!=null){
            binding.siteWeb.setOnClickListener(v ->{
                Uri uri = Uri.parse(website);
                Intent Siteintent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(Siteintent);
            });

        }else{
            binding.siteWeb.setOnClickListener(v -> {
                Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?q=" + Uri.encode(name + ", " + city + ", " + country + " (" + type + ")"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            });
        }



        // Affichage du numéro de téléphone
        if (phone != null && !phone.isEmpty()) {
            binding.lieuPhone.setText(phone);
        } else {
            binding.lieuPhone.setText(R.string.no_phone);
        }

        if (facebookPage != null) {
            binding.lieuFacebook.setText(facebookPage);
        } else {
            binding.facebook.setVisibility(View.GONE); // Cache la section Facebook si aucune donnée n'est disponible
        }

        if (twitterHandle != null) {
            binding.lieuTwitter.setText(twitterHandle);
        } else {
            binding.twitter.setVisibility(View.GONE); // Cache la section Twitter si aucune donnée n'est disponible
        }

        // Bouton pour revenir en arrière
        binding.backImage.setOnClickListener(view -> finish());

        // Gestion des favoris (exemple)

        // initialisation de LieuView;

        HotelViewModelFactory factory = new HotelViewModelFactory(apiKey);
        lieuViewModel = new ViewModelProvider(this,factory).get(LieuViewModel.class);
        favoris.setName(name);
        favoris.setAddress(address);
        favoris.setImageUrl(imageUrl);
        Boolean[] isFavorite = {false};
        binding.favImage.setOnClickListener(view -> {
            isFavorite[0] = !isFavorite[0];
            if (isFavorite[0]){
                binding.favImage.setImageResource(R.drawable.favorie);
                addFavoriteToFirebase(favoris);
            }
            else {
                lieuViewModel.setFavorisLiveData(new Favoris());
                lieuViewModel.setIsNewFavoris(false);
                binding.favImage.setImageResource(R.drawable.fav_icon);
                Toast.makeText(this, "supprimé aux favoris!", Toast.LENGTH_SHORT).show();
            }

        });



        // Affichage des autres détails généraux
        if (description != null && !description.isEmpty()) {
            binding.lieuDescription.setText(description);
        } else {
            binding.lieuDescription.setText(R.string.defaultDescription);
        }

//        if (country != null && !country.isEmpty()) {
//            binding.countryTextView.setText(country);
//        } else {
//            binding.countryTextView.setText(R.string.unknown_country);
//        }
//
//        if (city != null && !city.isEmpty()) {
//            binding.cityTextView.setText(city);
//        } else {
//            binding.cityTextView.setText(R.string.unknown_city);
//        }
    }

    private void addFavoriteToFirebase(Favoris favoris) {
        // Récupère l'UID de l'utilisateur depuis l'Intent
        String userId = "1";//getIntent().getStringExtra("USER_ID");

        if (userId == null || userId.isEmpty()) {
            Toast.makeText(this, "Utilisateur non authentifié", Toast.LENGTH_SHORT).show();
            return;
        }

        // Référence à la catégorie "hotel" pour cet utilisateur
         databaseref = FirebaseDatabase.getInstance()
                .getReference("Favoris")
                .child(userId)
                .child("hotel");

        // Génère un ID unique pour chaque favori
        String favoriteId = databaseref.push().getKey();
        if (favoriteId == null) {
            Toast.makeText(this, "Erreur Firebase", Toast.LENGTH_SHORT).show();
            return;
        }

        // Ajoute les données du favori
        databaseref.child(favoriteId).setValue(favoris).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Ajouté aux favoris!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Échec de l'ajout aux favoris", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
package com.example.voyageapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.voyageapp.adapter.SuggestedAdapter;
import com.example.voyageapp.model.PlacesList;
import com.example.voyageapp.model.SuggestedPlaces;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Home extends AppCompatActivity {

    RecyclerView suggestedRecycler;
    SuggestedAdapter suggestedAdapter;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> suggestionsList = new ArrayList<>();

    //attributs api
    private AutoCompleteTextView autoCompleteTextView;
    private OkHttpClient client;
    private static final String GEOAPIFY_API_KEY = "6e8d1afd50c748398154bb1fa279c11c";
    // Ajoutez cette liste pour stocker les coordonnées des lieux proposés
    private List<JSONObject> suggestedPlacesDetails = new ArrayList<>();
    FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView voirtous=findViewById(R.id.voir_tous);
        ImageView homeIcon = findViewById(R.id.icon_home);
        ImageView homeIconClic = findViewById(R.id.icon_home2);
        ImageView favoriIcon = findViewById(R.id.icon_favori);
        ImageView favoriIconClic = findViewById(R.id.icon_favori2);
        ImageView profileIcon = findViewById(R.id.icon_profile);
        ImageView profileIconClic = findViewById(R.id.icon_profile2);
        //2attributs autocomplete
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        client = new OkHttpClient();
        // Test de connectivité avec l'API Geoapify
        testApiConnection();
        // Configurez le clic sur une suggestion
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            try {
                // Vérifiez que la liste n'est pas vide
                if (suggestedPlacesDetails == null || suggestedPlacesDetails.isEmpty()) {
                    throw new Exception("La liste des lieux suggérés est vide !");
                }

                // Récupérez les détails de la ville sélectionnée
                JSONObject selectedPlace = suggestedPlacesDetails.get(position);
                if (!selectedPlace.has("geometry")) {
                    throw new Exception("L'objet sélectionné ne contient pas 'geometry'");
                }
                JSONObject geometry = selectedPlace.getJSONObject("geometry");
                JSONArray coordinates = geometry.getJSONArray("coordinates");
                if (coordinates.length() < 2) {
                    throw new Exception("Les coordonnées ne sont pas valides !");
                }
                // Récupérez les coordonnées
                double latitude = coordinates.getDouble(1);
                double longitude = coordinates.getDouble(0);
                Log.d("LocationDetails", "Latitude: " + latitude + ", Longitude: " + longitude);

                // Redirigez vers la page CategoryActivity avec les coordonnées :  // Passez les données à la prochaine activité

                Intent intent = new Intent(Home.this, CategoryActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("AutoCompleteError", "Erreur lors de la récupération des données", e);
                Toast.makeText(Home.this, "Erreur lors de la récupération des données", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Pas nécessaire ici
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 1) {
                    fetchSuggestions(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Pas nécessaire ici
            }
        });


        // Exemple de liste de lieux suggérés
        PlacesList placesList=new PlacesList();
        setSuggestedRecycler(placesList.getSuggestedPlaces());

        voirtous.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, SeeAll.class);
            //intent.putExtra("placesList", new ArrayList<>(suggestedPlacesList));
            startActivity(intent);
        });

        // Listener pour l'ImageView
        favoriIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Rediriger vers l'Activity favorites
                Intent intent = new Intent(Home.this, favoris.class);
                startActivity(intent);


            }
        });
        // Listener pour l'ImageView
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Rediriger vers l'Activity Profile
                Intent intent = new Intent(Home.this, Profil.class);
                //intent.putExtra("",1);
                startActivity(intent);
               /* profileIcon.setVisibility(View.GONE);
                profileIconClic.setVisibility(View.VISIBLE);
                favoriIcon.setVisibility(View.VISIBLE);
                favoriIconClic.setVisibility(View.GONE);
                homeIcon.setVisibility(View.VISIBLE);
                homeIconClic.setVisibility(View.GONE);*/

            }
        });
        String userId= currentUser.getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("utilisateur").child(userId);

        userRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                String prenom = task.getResult().child("prenom").getValue(String.class);
                TextView usercourant = findViewById(R.id.userCourant);
                usercourant.setText(prenom != null ? prenom : "Prénom non trouvé");
            } else {
                Toast.makeText(this, "Utilisateur non trouvé", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });

    }
    public void setSuggestedRecycler(List<SuggestedPlaces> suggestedPlaces) {
        suggestedRecycler = findViewById(R.id.recycler_v_suggested);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        suggestedRecycler.setLayoutManager(layoutManager);
        suggestedAdapter = new SuggestedAdapter(this, suggestedPlaces);
        suggestedRecycler.setAdapter(suggestedAdapter);
    }
    private void fetchSuggestions(String query) {
        String encodedQuery;
        try {
            encodedQuery = URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        String url = "https://api.geoapify.com/v1/geocode/autocomplete?text=" + encodedQuery + "&apiKey=" + GEOAPIFY_API_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                runOnUiThread(() -> {
                    Toast.makeText(Home.this, "Erreur réseau : " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        JSONArray features = jsonResponse.getJSONArray("features");
                        List<String> suggestions = new ArrayList<>();
                        suggestedPlacesDetails.clear(); // Nettoyez l'ancienne liste

                        for (int i = 0; i < features.length(); i++) {
                            JSONObject feature = features.getJSONObject(i);
                            JSONObject properties = feature.getJSONObject("properties");
                            String formatted = properties.getString("formatted");
                            suggestions.add(formatted);
                            // Stockez chaque détail de lieu
                            suggestedPlacesDetails.add(feature);
                        }

                        updateAutoCompleteSuggestions(suggestions);
                    } catch (Exception e) {
                        e.printStackTrace();
                        runOnUiThread(() -> Toast.makeText(Home.this, "Erreur de traitement des données", Toast.LENGTH_SHORT).show());
                    }
                } else {
                    runOnUiThread(() -> Toast.makeText(Home.this, "Erreur HTTP : " + response.code(), Toast.LENGTH_LONG).show());
                }
            }
        });
    }
    private void updateAutoCompleteSuggestions(List<String> suggestions) {
        runOnUiThread(() -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(Home.this,
                    android.R.layout.simple_dropdown_item_1line, suggestions);
            autoCompleteTextView.setAdapter(adapter);
            autoCompleteTextView.showDropDown();
        });
    }
    private void testApiConnection() {
        new Thread(() -> {
            try {
                URL url = new URL("https://api.geoapify.com");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) { // Code HTTP OK
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.d("API Test", "Connected successfully: " + response.toString());
                } else {
                    Log.e("API Test", "Failed to connect. Response code: " + responseCode);
                }
            } catch (Exception e) {
                Log.e("API Test", "Connection failed: " + e.getMessage());
            }
        }).start();
    }

}

// bonjour
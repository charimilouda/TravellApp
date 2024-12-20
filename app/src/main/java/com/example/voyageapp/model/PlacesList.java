package com.example.voyageapp.model;


import com.example.voyageapp.R;

import java.util.ArrayList;
import java.util.List;

public class PlacesList {
    // Méthode pour obtenir la liste des lieux suggérés
    public List<SuggestedPlaces> getSuggestedPlaces() {
        List<SuggestedPlaces> suggestedPlacesList = new ArrayList<>();

        suggestedPlacesList.add(new SuggestedPlaces("Hotel Al Kabir", "Marrakech, Maroc", "Hotel", R.drawable.hotelalkabir));
        suggestedPlacesList.add(new SuggestedPlaces("Restaurant Tafoughalt", "Tafoughalt, Morocco", "Restaurant", R.drawable.restauafoughalt));
        suggestedPlacesList.add(new SuggestedPlaces("Radisson Blu Resort", "Saidia, Maroc", "Hotel", R.drawable.radissonblursaidia));
        suggestedPlacesList.add(new SuggestedPlaces("Kahayana Suites Ubud", "Ubud, Bali", "Hotel", R.drawable.balihotel));
        suggestedPlacesList.add(new SuggestedPlaces("Diament Vert", "Fes, Maroc", "Hotel", R.drawable.diamentvert));
        suggestedPlacesList.add(new SuggestedPlaces("Kenzi Rose Garden", "Marrakech, Morocco", "Hotel", R.drawable.kenzirosegarden));
        suggestedPlacesList.add(new SuggestedPlaces("Hivernage Hotel & Spa", "Marrakech, Morocco", "Hotel", R.drawable.hivernagehotelspa));
        suggestedPlacesList.add(new SuggestedPlaces("The CIVYT", "Ubud, Indonésie", "Hotel", R.drawable.thecivity));
        suggestedPlacesList.add(new SuggestedPlaces("Nordstern Hotel Galata", "Istanbul, Turquie", "Hotel", R.drawable.nordsternhotelgalata));

        return suggestedPlacesList;
    }
}

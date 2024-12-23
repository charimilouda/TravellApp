package com.example.voyageapp.viewModel;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import com.example.voyageapp.data.model.Cafe;
import com.example.voyageapp.data.model.CentreCommercial;
import com.example.voyageapp.data.model.Favoris;
import com.example.voyageapp.data.model.Hotel;
import com.example.voyageapp.data.model.Musee;
import com.example.voyageapp.data.model.Parc;
import com.example.voyageapp.data.model.Restaurant;
import com.example.voyageapp.data.repository.LieuRepository;

public class LieuViewModel extends ViewModel {
    private final String apiKey;
    private final LieuRepository repository = new LieuRepository();

    // Les livadata des categorie
    private LiveData<List<Hotel>> HotelsLiveData;
    private LiveData<List<Musee>> MuseeLiveData;
    private LiveData<List<Restaurant>> RestaurantLiveData;
    private LiveData<List<Cafe>> CafeLiveData;
    private LiveData<List<CentreCommercial>> CentreLiveData;
    private LiveData<List<Parc>>  ParcLiveData;


    //les livadata de controle
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> filter = new MutableLiveData<>();
    private MutableLiveData<String> categorie = new MutableLiveData<>();


    private MutableLiveData<Favoris> favorisLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isNewFavoris = new MutableLiveData<>();



    public LieuViewModel(String apiKey){
        combinedData.addSource(categorie, value -> updateCombinedData());
        combinedData.addSource(filter, value -> updateCombinedData());
        this.apiKey = apiKey;
    }



    private void updateCombinedData() {
        String cat = categorie.getValue();
        String fil = filter.getValue();
        if (cat != null && fil != null) {
            combinedData.setValue(new Pair<>(cat, fil));
        }
    }

    // les methode de fetching  et de traitement

    public void setIsNewFavoris(Boolean isNewFavorie) {
        this.isNewFavoris.setValue(isNewFavorie);
    }


    public LiveData<Boolean> getIsNewFavoris() {
        return this.isNewFavoris;
    }

    public LiveData<Favoris> getFavorisLiveData() {
        return favorisLiveData;
    }
    public void setFavorisLiveData(Favoris favorisLiveData) {
        this.favorisLiveData.setValue(favorisLiveData);
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(Boolean isLoading) {
        this.isLoading.setValue(isLoading);
    }

    public LiveData<String> getCategorie() {
        return categorie;
    }

    public void setCategorie(String newCategorie) {
        if (categorie.getValue() == null || !categorie.getValue().equals(newCategorie)) {
            categorie.setValue(newCategorie); // Met à jour si une nouvelle catégorie est sélectionnée
        }
    }


    public LiveData<String> getFilter(){
        return this.filter;
    }

    public void setFilter(String newFilter) {
        if (filter.getValue() == null || !filter.getValue().equals(newFilter)) {
            filter.setValue(newFilter); // Met à jour si un nouveau filtre est sélectionné
        }
    }

    public MediatorLiveData<Pair<String, String>> getCombinedData() {
        return combinedData;
    }

    private final MediatorLiveData<Pair<String, String>> combinedData = new MediatorLiveData<>();



    // La recuperation des hotels, retaurants,.. etc, via le Les methode getHotels
    // respectivement getRestaurants de LieuRepository

    public LiveData<List<Hotel>>  getHotelsLiveData(String categorie, String filter)
    {   if (HotelsLiveData == null)
        {   setIsLoading(true);
            this.HotelsLiveData = repository.getHotels(categorie, filter, apiKey);
            setIsLoading(false);
        }
        return this.HotelsLiveData;
    }

    public LiveData<List<Restaurant>> getRestaurantLiveData(String categorie, String filter) {
        if (RestaurantLiveData == null){
            setIsLoading(true);
            this.RestaurantLiveData = this.repository.getRestaurant(categorie, filter, this.apiKey);
            setIsLoading(false);
        }

        return RestaurantLiveData;
    }

    public LiveData<List<Cafe>> getCafeLiveData(String categorie, String filter){
        if(CafeLiveData == null){

            setIsLoading(true);
            this.CafeLiveData = this.repository.getCafe(categorie,filter,this.apiKey);
            setIsLoading(false);
        }

        return CafeLiveData;
    }

    public LiveData<List<CentreCommercial>>  getCentreLiveDate(String categorie, String filtter){

        if(CentreLiveData == null){
            setIsLoading(true);
            this.CentreLiveData = this.repository.getCentreCommercial(categorie, filtter, this.apiKey);
            setIsLoading(false);
        }

        return CentreLiveData;
    }

    public LiveData<List<Musee>>  getMuseeLiveDate(String categorie, String filter){

        if(MuseeLiveData == null){
            setIsLoading(true);
            this.MuseeLiveData = repository.getMuse(categorie,filter,apiKey);
            setIsLoading(false);

        }

        return MuseeLiveData;

    }


    public LiveData<List<Parc>> getParcLiveDate(String categorie, String filter) {

        if (ParcLiveData == null){
            setIsLoading(true);
            this.ParcLiveData = this.repository.getParc(categorie,filter,this.apiKey);
            setIsLoading(false);

        }

        return ParcLiveData;
    }


    public String searchCategorie(String categorie){

        switch (categorie.toLowerCase()){
            case "hotel":
                return "accommodation.hotel";

            case "restaurant":
                return "catering.restaurant";


            case "shopping":
                return "commercial.supermarket,commercial.marketplace,commercial.shopping_mall,commercial.department_store";

            case "cafe":
                return "catering.cafe.coffee_shop";

            case "park":
                return "leisure.park";

            case "museum":
                return "entertainment.museum";

            default:
                return "";
        }

    }



}

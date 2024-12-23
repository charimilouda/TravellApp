package com.example.voyageapp.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import com.example.voyageapp.data.mapper.Mapper;
import com.example.voyageapp.data.model.Cafe;
import com.example.voyageapp.data.model.CentreCommercial;
import com.example.voyageapp.data.model.Hotel;
import com.example.voyageapp.data.model.Musee;
import com.example.voyageapp.data.model.Parc;
import com.example.voyageapp.data.model.Restaurant;
import com.example.voyageapp.data.responseModel.CafeResponse;
import com.example.voyageapp.data.responseModel.CentreCommercialResponse;
import com.example.voyageapp.data.responseModel.HotelsResponse;
import com.example.voyageapp.data.responseModel.MuseResponse;
import com.example.voyageapp.data.responseModel.ParcResponse;
import com.example.voyageapp.data.responseModel.RestaurantResponse;
import com.example.voyageapp.data.responseModel.UnsplashResponse;
import com.example.voyageapp.data.service.ApiServices;
import com.example.voyageapp.data.service.OnImageFetchedCallback;
import com.example.voyageapp.data.service.RetrofitClient;
import com.example.voyageapp.data.service.RetrofitClient2;
import com.example.voyageapp.data.service.UnsplashApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LieuRepository {
    private final ApiServices apiServices;
    private final UnsplashApi unsplashApi;
    private final String CLIENT_ID = "tDmn1MPQNdGXKS-0XJN3RAjirBMX_Vton7Vj7fied3w";
    public LieuRepository()
    {
        this.unsplashApi = RetrofitClient2.getRetrofit();
        this.apiServices = RetrofitClient.getRetrofitInstance().create(ApiServices.class);
    }

    public LiveData<List<Hotel>> getHotels(String categorie, String filter, String apiKey){

        MutableLiveData<List<Hotel>> HotelsLiveData = new MutableLiveData<>();

        Call<HotelsResponse> call = apiServices.getHotels(
                categorie,
                filter,
                apiKey
        );

        call.enqueue(new Callback<HotelsResponse>() {
            @Override
            public void onResponse(@NonNull Call<HotelsResponse> call, @NonNull Response<HotelsResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    List<Hotel> hotels = Mapper.ResponseToHotels(response.body());
                    for (Hotel hotel : hotels) {
                        String query =hotel.getName() + " " + hotel.getType() ;

                        fetchImageForLieu(query, 1, 1, CLIENT_ID, new OnImageFetchedCallback() {
                            @Override
                            public void onSuccess(String imageUrl) {
                                hotel.setImageUrl(imageUrl);

                                // Postez les données dès que toutes les images sont récupérées
                                HotelsLiveData.postValue(hotels);
                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                hotel.setImageUrl("https://plus.unsplash.com/premium_photo-1661962360690-e91cc0df88f1?q=80&w=1780&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"); // URL par défaut
                                Log.e("LieuRepository", "Image fetch failed: " + errorMessage);

                                // Postez les données avec une image par défaut
                                HotelsLiveData.postValue(hotels);
                            }
                        });
                    }

                }
                else {
                    HotelsLiveData.postValue(null);
                    Log.e("LieuRepository", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<HotelsResponse> call, @NonNull Throwable t) {
                HotelsLiveData.postValue(null);
                Log.e("LieuRepository", "Error: " + t.getMessage());
            }
        });


        return HotelsLiveData;

    }

    public LiveData<List<Restaurant>> getRestaurant(String categorie, String filter, String apiKey){
        MutableLiveData<List<Restaurant>> restaurantLiveData = new MutableLiveData<>();

        Call<RestaurantResponse>call = apiServices.getRestaurant(
                categorie,
                filter,
                apiKey
        );

        call.enqueue(new Callback<RestaurantResponse>() {
            @Override
            public void onResponse(Call<RestaurantResponse> call, Response<RestaurantResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    List<Restaurant> restaurants = Mapper.ResponseToRestaurants(response.body());
                    for (Restaurant restaurant : restaurants) {
                        String query =restaurant.getType() + " " + restaurant.getName();

                        fetchImageForLieu(query, 1, 1, CLIENT_ID, new OnImageFetchedCallback() {
                            @Override
                            public void onSuccess(String imageUrl) {
                                restaurant.setImageUrl(imageUrl);

                                // Postez les données dès que toutes les images sont récupérées
                                restaurantLiveData.postValue(restaurants);
                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                restaurant.setImageUrl("https://images.unsplash.com/photo-1471623320832-752e8bbf8413?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2ODc1OTB8MHwxfHNlYXJjaHwxfHxIaWx0b24lMjBIb3RlbCUyMFBhcmlzfGVufDB8fHx8MTczNDU1MDYyOXww&ixlib=rb-4.0.3&q=80&w=1080"); // URL par défaut
                                Log.e("LieuRepository", "Image fetch failed: " + errorMessage);

                                // Postez les données avec une image par défaut
                                restaurantLiveData.postValue(restaurants);
                            }
                        });
                    }

                }else {
                    restaurantLiveData.postValue(null);
                    Log.e("LieuRepository", "Error" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantResponse> call, @NonNull Throwable t) {
                    restaurantLiveData.postValue(null);
                    Log.e("LieuRepository", "Error : "+t.getMessage());
            }
        });

        return restaurantLiveData;
    }

    public LiveData<List<Cafe>> getCafe(String categorie, String filter, String apiKey){
        MutableLiveData<List<Cafe>> cafesLiveData = new MutableLiveData<>();

        Call<CafeResponse>call = apiServices.getCafe(
                categorie,
                filter,
                apiKey
        );

        call.enqueue(new Callback<CafeResponse>() {
            @Override
            public void onResponse(@NonNull Call<CafeResponse> call, Response<CafeResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    List<Cafe> cafes = Mapper.ResponseToCafe(response.body());
                    for (Cafe cafe : cafes) {
                        String query =cafe.getType() + " " + cafe.getName();

                        fetchImageForLieu(query, 1, 1, CLIENT_ID, new OnImageFetchedCallback() {
                            @Override
                            public void onSuccess(String imageUrl) {
                                cafe.setImageUrl(imageUrl);

                                // Postez les données dès que toutes les images sont récupérées
                                cafesLiveData.postValue(cafes);
                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                cafe.setImageUrl("https://images.unsplash.com/photo-1471623320832-752e8bbf8413?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2ODc1OTB8MHwxfHNlYXJjaHwxfHxIaWx0b24lMjBIb3RlbCUyMFBhcmlzfGVufDB8fHx8MTczNDU1MDYyOXww&ixlib=rb-4.0.3&q=80&w=1080"); // URL par défaut
                                Log.e("LieuRepository", "Image fetch failed: " + errorMessage);

                                // Postez les données avec une image par défaut
                                cafesLiveData.postValue(cafes);
                            }
                        });
                    }

                }else {
                    cafesLiveData.postValue(null);
                    Log.e("LieuRepository", "Error" + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CafeResponse> call, @NonNull Throwable t) {
                cafesLiveData.postValue(null);
                Log.e("LieuRepository", "Error : "+t.getMessage());
            }
        });

        return cafesLiveData;
    }

    public LiveData<List<CentreCommercial>> getCentreCommercial(String categorie, String filter, String apiKey){
        MutableLiveData<List<CentreCommercial>> centreLiveData = new MutableLiveData<>();

        Call<CentreCommercialResponse> call = apiServices.getCentreCommercial(
                categorie,
                filter,
                apiKey
        );

        call.enqueue(new Callback<CentreCommercialResponse>() {
            @Override
            public void onResponse(@NonNull Call<CentreCommercialResponse> call, Response<CentreCommercialResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    List<CentreCommercial> CentreCommercials = Mapper.ResponseToCentreCommercial(response.body());
                    for (CentreCommercial centre : CentreCommercials) {
                        String query =centre.getType() + " " +centre.getName();
                        fetchImageForLieu(query, 1, 1, CLIENT_ID, new OnImageFetchedCallback() {
                            @Override
                            public void onSuccess(String imageUrl) {
                                centre.setImageUrl(imageUrl);

                                // Postez les données dès que toutes les images sont récupérées
                                centreLiveData.postValue(CentreCommercials);
                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                centre.setImageUrl("https://images.unsplash.com/photo-1471623320832-752e8bbf8413?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2ODc1OTB8MHwxfHNlYXJjaHwxfHxIaWx0b24lMjBIb3RlbCUyMFBhcmlzfGVufDB8fHx8MTczNDU1MDYyOXww&ixlib=rb-4.0.3&q=80&w=1080"); // URL par défaut
                                Log.e("LieuRepository", "Image fetch failed: " + errorMessage);

                                // Postez les données avec une image par défaut
                                centreLiveData.postValue(CentreCommercials);
                            }
                        });
                    }
                }else {
                    centreLiveData.postValue(null);
                    Log.e("LieuRepository", "Error" + response.code());
                }
            }

            @Override
            public void onFailure(Call<CentreCommercialResponse> call, Throwable t) {
                centreLiveData.postValue(null);
                Log.e("LieuRepository", "Error : "+t.getMessage());
            }
        });

        return centreLiveData;
    }

    public LiveData<List<Musee>> getMuse(String categorie, String filter, String apiKey){
        MutableLiveData<List<Musee>> museLiveData = new MutableLiveData<>();

        Call<MuseResponse> call = apiServices.getMuse(
                categorie,
                filter,
                apiKey
        );

        call.enqueue(new Callback<MuseResponse>() {
            @Override
            public void onResponse(Call<MuseResponse> call, Response<MuseResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    List<Musee> muses = Mapper.ResponseToMusee(response.body());
                    for (Musee muse : muses) {
                        String query =muse.getType() + " " + muse.getName();
                        fetchImageForLieu(query, 1, 1, CLIENT_ID, new OnImageFetchedCallback() {
                            @Override
                            public void onSuccess(String imageUrl) {
                                muse.setImageUrl(imageUrl);

                                // Postez les données dès que toutes les images sont récupérées
                                museLiveData.postValue(muses);
                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                muse.setImageUrl("https://images.unsplash.com/photo-1471623320832-752e8bbf8413?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2ODc1OTB8MHwxfHNlYXJjaHwxfHxIaWx0b24lMjBIb3RlbCUyMFBhcmlzfGVufDB8fHx8MTczNDU1MDYyOXww&ixlib=rb-4.0.3&q=80&w=1080"); // URL par défaut
                                Log.e("LieuRepository", "Image fetch failed: " + errorMessage);

                                // Postez les données avec une image par défaut
                                museLiveData.postValue(muses);
                            }
                        });
                    }

                }
                else {
                    museLiveData.postValue(null);
                    Log.e("LieuRepository", "Error" + response.code());
                }
            }

            @Override
            public void onFailure(Call<MuseResponse> call, Throwable t) {
                museLiveData.postValue(null);
                Log.e("LieuRepository", "Error : "+t.getMessage());
            }
        });

        return museLiveData;
    }

    public LiveData<List<Parc>> getParc(String categorie, String filter, String apiKey){
        MutableLiveData<List<Parc>> parcLiveData = new MutableLiveData<>();

        Call<ParcResponse> call = apiServices.getParc(
                categorie,
                filter,
                apiKey
        );

        call.enqueue(new Callback<ParcResponse>() {
            @Override
            public void onResponse(Call<ParcResponse> call, Response<ParcResponse> response) {
                if (response.isSuccessful() && response.body()!=null){
                    List<Parc> parcs = Mapper.ResponseToParc(response.body());
                    for (Parc parc : parcs) {
                        String query =parc.getType() + " " + parc.getName();
                        fetchImageForLieu(query, 1, 1, CLIENT_ID, new OnImageFetchedCallback() {
                            @Override
                            public void onSuccess(String imageUrl) {
                                if (parc.getImageUrl()==null || parc.getImageUrl().isEmpty()){
                                    parc.setImageUrl(imageUrl);
                                }

                                // Postez les données dès que toutes les images sont récupérées
                                parcLiveData.postValue(parcs);
                            }

                            @Override
                            public void onFailure(String errorMessage) {
                                parc.setImageUrl("https://images.unsplash.com/photo-1471623320832-752e8bbf8413?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w2ODc1OTB8MHwxfHNlYXJjaHwxfHxIaWx0b24lMjBIb3RlbCUyMFBhcmlzfGVufDB8fHx8MTczNDU1MDYyOXww&ixlib=rb-4.0.3&q=80&w=1080"); // URL par défaut
                                Log.e("LieuRepository", "Image fetch failed: " + errorMessage);

                                // Postez les données avec une image par défaut
                                parcLiveData.postValue(parcs);
                            }
                        });
                    }

                }
                else {
                    parcLiveData.postValue(null);
                    Log.e("LieuRepository", "Error" + response.code());
                }
            }

            @Override
            public void onFailure(Call<ParcResponse> call, Throwable t) {
                parcLiveData.postValue(null);
                Log.e("LieuRepository", "Error : "+t.getMessage());
            }
        });

        return parcLiveData;
    }

    private void fetchImageForLieu(String query, int page, int per_page, String client_id, OnImageFetchedCallback callback) {

        unsplashApi.searchPhotos(query,page,per_page,client_id).enqueue(new Callback<UnsplashResponse>() {
            @Override
            public void onResponse(Call<UnsplashResponse> call, Response<UnsplashResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UnsplashResponse.Result> results = response.body().getResults();
                    if (!results.isEmpty()) {
                        String imageUrl = results.get(0).getUrls().getRegular(); // Première image trouvée
                        callback.onSuccess(imageUrl);
                    } else {
                        callback.onFailure("No image found");
                    }
                } else {
                    callback.onFailure("API error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UnsplashResponse> call, Throwable t) {
                callback.onFailure("Request failed: " + t.getMessage());
            }
        });

    }


}

package com.example.voyageapp.data.service;


import com.example.voyageapp.data.responseModel.CafeResponse;
import com.example.voyageapp.data.responseModel.CentreCommercialResponse;
import com.example.voyageapp.data.responseModel.HotelsResponse;
import com.example.voyageapp.data.responseModel.MuseResponse;
import com.example.voyageapp.data.responseModel.ParcResponse;
import com.example.voyageapp.data.responseModel.RestaurantResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("/v2/places")
    Call<HotelsResponse> getHotels(
            @Query("categories") String category,
            @Query("filter") String filter,
            @Query("apiKey") String apiKey
    );

    @GET("/v2/places")
    Call<RestaurantResponse> getRestaurant(
            @Query("categories") String category,
            @Query("filter") String filter,
            @Query("apiKey") String apiKey
    );

    @GET("/v2/places")
    Call<CafeResponse> getCafe(
            @Query("categories") String category,
            @Query("filter") String filter,
            @Query("apiKey") String apiKey
    );

    @GET("/v2/places")
    Call<CentreCommercialResponse> getCentreCommercial(
            @Query("categories") String category,
            @Query("filter") String filter,
            @Query("apiKey") String apiKey
    );

    @GET("/v2/places")
    Call<MuseResponse> getMuse(
            @Query("categories") String category,
            @Query("filter") String filter,
            @Query("apiKey") String apiKey
    );

    @GET("/v2/places")
    Call<ParcResponse> getParc(
            @Query("categories") String category,
            @Query("filter") String filter,
            @Query("apiKey") String apiKey
    );
}

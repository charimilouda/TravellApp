package com.example.voyageapp.data.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient2 {
    private static UnsplashApi api;
    private final static String URL_BASE = "https://api.unsplash.com/";

    public static UnsplashApi getRetrofit(){

        if (api == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = retrofit.create(UnsplashApi.class);
        }

        return api;
    }
}

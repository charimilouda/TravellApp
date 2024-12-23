package com.example.voyageapp.viewModel.injection;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.voyageapp.viewModel.LieuViewModel;

public class HotelViewModelFactory implements ViewModelProvider.Factory {
    private final String apiKey;

    public HotelViewModelFactory(String apiKey) {
        this.apiKey = apiKey;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LieuViewModel.class)) {
            return modelClass.cast(new LieuViewModel(apiKey));
        }
        else throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
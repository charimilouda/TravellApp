package com.example.voyageapp.data.service;

public interface OnImageFetchedCallback {
    void onSuccess(String imageUrl);
    void onFailure(String errorMessage);
}

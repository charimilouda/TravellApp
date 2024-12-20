package com.example.voyageapp.model;

public class SuggestedPlaces {
    String placeName;
    String countryName;
    String categorie;
    Integer imageUrl;

    public SuggestedPlaces(String placeName, String countryName, String categorie, Integer imageUrl) {
        this.placeName = placeName;
        this.countryName = countryName;
        this.categorie = categorie;
        this.imageUrl=imageUrl;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}

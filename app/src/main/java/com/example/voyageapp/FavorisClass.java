package com.example.voyageapp;

public class FavorisClass {
    private String name;
    private String address;
    private String imageUrl;

    public FavorisClass() {
    }

    public FavorisClass(String name, String address, String imageUrl) {
        this.name = name;
        this.address = address;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() { // Use "address" instead of "location"
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() { // Use "imageUrl" instead of "imageURL"
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

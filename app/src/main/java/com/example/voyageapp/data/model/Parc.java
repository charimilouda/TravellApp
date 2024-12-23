package com.example.voyageapp.data.model;

import java.util.HashMap;
import java.util.Map;

public class Parc extends Lieu{

    private final String type = "Parc";
    private String openingHours = "08h00-19h00 7/7"; // Horaires d'ouverture
    private boolean wheelchairAccessible =true; // Accessibilité fauteuil roulant
    private int heritageLevel; // Niveau de patrimoine
    private String panoramaImageUrl; // URL de l'image panoramique
    private Map<String, String> nameInInternationalLanguages = new HashMap<>(); // Noms dans différentes langues

    public String getType() {
        return type;
    }

    public Parc() {
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public boolean isWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public int getHeritageLevel() {
        return heritageLevel;
    }

    public void setHeritageLevel(int heritageLevel) {
        this.heritageLevel = heritageLevel;
    }

    public String getPanoramaImageUrl() {
        return panoramaImageUrl;
    }

    public void setPanoramaImageUrl(String panoramaImageUrl) {
        this.panoramaImageUrl = panoramaImageUrl;
    }

    public Map<String, String> getNameInInternationalLanguages() {
        return nameInInternationalLanguages;
    }

    public void setNameInInternationalLanguages(Map<String, String> nameInInternationalLanguages) {
        this.nameInInternationalLanguages = nameInInternationalLanguages;
    }


}

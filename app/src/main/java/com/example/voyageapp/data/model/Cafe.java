package com.example.voyageapp.data.model;

public class Cafe extends Lieu {
    private String internationalName; // Nom international
    private String phone; // Téléphone

    public String getType() {
        return type;
    }

    private final String type = "Cafe";
    private String openingHours = "06h30-23h00 7/7"; // Horaires d'ouverture
    private boolean wheelchairAccessible = true; // Accessibilité fauteuil roulant
    private boolean internetAccess =true; // Internet disponible
    private boolean airConditioning = true; // Climatisation
    private boolean outdoorSeating = true; // Places assises à l'extérieur
    private String wikidataLink; // Lien vers Wikidata

    public Cafe(){
        super();
    }


    public String getInternationalName() {
        return internationalName;
    }

    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public boolean isInternetAccess() {
        return internetAccess;
    }

    public void setInternetAccess(boolean internetAccess) {
        this.internetAccess = internetAccess;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public boolean isOutdoorSeating() {
        return outdoorSeating;
    }

    public void setOutdoorSeating(boolean outdoorSeating) {
        this.outdoorSeating = outdoorSeating;
    }

    public String getWikidataLink() {
        return wikidataLink;
    }

    public void setWikidataLink(String wikidataLink) {
        this.wikidataLink = wikidataLink;
    }
}

package com.example.voyageapp.data.model;

public class CentreCommercial extends Lieu {

    private String phone; // Téléphone

    public String getType() {
        return type;
    }

    private final String type = "Centre Commercial";
    private String openingHours = "08h:00-22h:00"; // Horaires d'ouverture
    private boolean wheelchairAccessible; // Accessibilité fauteuil roulant
    private boolean hasToilets = true; // Toilettes disponibles
    private String buildingType; // Type de bâtiment (retail)
    private int buildingHeight; // Hauteur du bâtiment
    private int startDate; // Année de construction
    private String commercialType; // Type commercial (mall)
    private String wikidataLink; // Lien Wikidata
    private String wikipediaLink; // Lien Wikipedia

    public CentreCommercial(){
        super();
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

    public boolean isHasToilets() {
        return hasToilets;
    }

    public void setHasToilets(boolean hasToilets) {
        this.hasToilets = hasToilets;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public int getBuildingHeight() {
        return buildingHeight;
    }

    public void setBuildingHeight(int buildingHeight) {
        this.buildingHeight = buildingHeight;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public String getCommercialType() {
        return commercialType;
    }

    public void setCommercialType(String commercialType) {
        this.commercialType = commercialType;
    }

    public String getWikidataLink() {
        return wikidataLink;
    }

    public void setWikidataLink(String wikidataLink) {
        this.wikidataLink = wikidataLink;
    }

    public String getWikipediaLink() {
        return wikipediaLink;
    }

    public void setWikipediaLink(String wikipediaLink) {
        this.wikipediaLink = wikipediaLink;
    }
}

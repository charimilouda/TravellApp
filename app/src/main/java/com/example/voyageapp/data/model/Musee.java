package com.example.voyageapp.data.model;

public class Musee extends Lieu {
    private String phone; // Téléphone

    public String getType() {
        return type;
    }

    private final String type = "Musée";
    private String openingHours = "08h00-18h00 7/7"; // Horaires d'ouverture
    private String operator; // Opérateur
    private boolean wheelchairAccessible = true; // Accessibilité fauteuil roulant
    private int heritageLevel; // Niveau de patrimoine
    private String heritageRef; // Référence patrimoine
    private int heritageInscriptionDate; // Date d'inscription
    private String buildingType; // Type de bâtiment (e.g., "church")
    private int buildingHeight; // Hauteur du bâtiment
    private int buildingStartDate; // Année de construction
    private String wikidataLink; // Lien Wikidata
    private String wikipediaLink; // Lien Wikipedia
    private String wikimediaCommons; // Wikimedia Commons

    public Musee(){
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public String getHeritageRef() {
        return heritageRef;
    }

    public void setHeritageRef(String heritageRef) {
        this.heritageRef = heritageRef;
    }

    public int getHeritageInscriptionDate() {
        return heritageInscriptionDate;
    }

    public void setHeritageInscriptionDate(int heritageInscriptionDate) {
        this.heritageInscriptionDate = heritageInscriptionDate;
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

    public int getBuildingStartDate() {
        return buildingStartDate;
    }

    public void setBuildingStartDate(int buildingStartDate) {
        this.buildingStartDate = buildingStartDate;
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

    public String getWikimediaCommons() {
        return wikimediaCommons;
    }

    public void setWikimediaCommons(String wikimediaCommons) {
        this.wikimediaCommons = wikimediaCommons;
    }
}

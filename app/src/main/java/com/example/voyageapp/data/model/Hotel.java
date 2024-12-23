package com.example.voyageapp.data.model;

public class Hotel extends Lieu {
    private String opening_hours = "24h/24h 7/7";
    private String altName; // Nom alternatif
    private String phone;
    private final String type = "hotel";// Téléphone
    private boolean wheelchairAccessible = true; // Accessibilité fauteuil roulant
    private int buildingLbevels = 4; // Nombre d'étages


    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }

    public Hotel() {
        super();
    }

    public String getAltName() {
        return altName;
    }

    public void setAltName(String altName) {
        this.altName = altName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType(){
        return this.type;
    }

    public boolean isWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public int getBuildingLbevels() {
        return buildingLbevels;
    }

    public void setBuildingLbevels(int buildingLbevels) {
        this.buildingLbevels = buildingLbevels;
    }
}

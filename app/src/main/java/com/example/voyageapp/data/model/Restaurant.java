package com.example.voyageapp.data.model;

public class Restaurant extends Lieu {

    private String oldName; // Ancien nom
    private String phone; // Téléphone

    public String getType() {
        return type;
    }

    private final String type = "Restaurant";
    private String email; // Email
    private String openingHours = "08h00-00h00 7/7"; // Horaires d'ouverture
    private String description; // Description
    private boolean wheelchairAccessible = true; // Accessibilité fauteuil roulant
    private boolean wheelchairToilets = true; // Toilettes accessibles
    private String twitterHandle; // Compte Twitter
    private String facebookPage; // Page Facebook

    public Restaurant() {
        super();
    }


    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(boolean wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    public boolean isWheelchairToilets() {
        return wheelchairToilets;
    }

    public void setWheelchairToilets(boolean wheelchairToilets) {
        this.wheelchairToilets = wheelchairToilets;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    public String getFacebookPage() {
        return facebookPage;
    }

    public void setFacebookPage(String facebookPage) {
        this.facebookPage = facebookPage;
    }
}

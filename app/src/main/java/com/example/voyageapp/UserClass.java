package com.example.voyageapp;

public class UserClass {

    private String Email;
    private String Emplacement;
    private String ImageUrl;
    private String Motdepass;
    private String Id;
    private String Prenom;
    private String Nom;
    private String Numerotele;

    public String getEmail() {
        return Email;
    }

    public String getNumerotele() {
        return Numerotele;
    }

    public String getNom() {
        return Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public String getId() {
        return Id;
    }

    public String getMotdepass() {
        return Motdepass;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getEmplacement() {
        return Emplacement;
    }

    public UserClass() {
    }

    public UserClass(String email, String emplacement, String imageUrl, String motdepass, String id, String prenom, String nom, String numerotele) {
        Email = email;
        Emplacement = emplacement;
        ImageUrl = imageUrl;
        Motdepass = motdepass;
        Id = id;
        Prenom = prenom;
        Nom = nom;
        Numerotele = numerotele;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setEmplacement(String emplacement) {
        Emplacement = emplacement;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public void setMotdepass(String motdepass) {
        Motdepass = motdepass;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public void setNumerotele(String numerotele) {
        Numerotele = numerotele;
    }
}

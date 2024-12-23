package com.example.voyageapp.data.responseModel;

import java.util.List;

public class RestaurantResponse {
    private String type; // Type de la réponse, par exemple "FeatureCollection"
    private List<Feature> features; // Liste des features (restaurants)

    // Getters et setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    // Classe interne représentant une "Feature"
    public static class Feature {
        private String type; // Type de la feature, par exemple "Feature"
        private Properties properties; // Les propriétés du restaurant
        private Geometry geometry; // La géométrie (coordonnées)

        // Getters et setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Properties getProperties() {
            return properties;
        }

        public void setProperties(Properties properties) {
            this.properties = properties;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        // Classe interne pour les propriétés du restaurant
        public static class Properties {
            private String name; // Nom du restaurant
            private String oldName; // Ancien nom (nom alternatif)
            private String country;
            private String city;
            private String formatted; // Adresse complète
            private String addressLine1; // Ligne 1 de l'adresse
            private String addressLine2; // Ligne 2 de l'adresse
            private String openingHours; // Horaires d'ouverture
            private Contact contact; // Informations de contact
            private String website; // Lien vers le site web
            private String description; // Description du restaurant
            private Facilities facilities; // Accessibilité et autres équipements
            private Datasource datasource; // Informations sur la source des données

            // Getters et setters
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOldName() {
                return oldName;
            }

            public void setOldName(String oldName) {
                this.oldName = oldName;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getFormatted() {
                return formatted;
            }

            public void setFormatted(String formatted) {
                this.formatted = formatted;
            }

            public String getAddressLine1() {
                return addressLine1;
            }

            public void setAddressLine1(String addressLine1) {
                this.addressLine1 = addressLine1;
            }

            public String getAddressLine2() {
                return addressLine2;
            }

            public void setAddressLine2(String addressLine2) {
                this.addressLine2 = addressLine2;
            }


            public String getOpeningHours() {
                return openingHours;
            }

            public void setOpeningHours(String openingHours) {
                this.openingHours = openingHours;
            }

            public Contact getContact() {
                return contact;
            }

            public void setContact(Contact contact) {
                this.contact = contact;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Facilities getFacilities() {
                return facilities;
            }

            public void setFacilities(Facilities facilities) {
                this.facilities = facilities;
            }

            public Datasource getDatasource() {
                return datasource;
            }

            public void setDatasource(Datasource datasource) {
                this.datasource = datasource;
            }

            // Classe interne pour les informations de contact
            public static class Contact {
                private String phone; // Téléphone
                private String email; // Email

                // Getters et setters
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
            }

            // Classe interne pour les équipements/accessibilité
            public static class Facilities {
                private boolean wheelchair; // Accessibilité fauteuil roulant
                private boolean toilets; // Toilettes accessibles

                // Getters et setters
                public boolean isWheelchair() {
                    return wheelchair;
                }

                public void setWheelchair(boolean wheelchair) {
                    this.wheelchair = wheelchair;
                }

                public boolean isToilets() {
                    return toilets;
                }

                public void setToilets(boolean toilets) {
                    this.toilets = toilets;
                }
            }

            // Classe interne pour les informations de la source des données
            public static class Datasource{
                private Raw raw;

                // Getters et setters
                public Raw getRaw() {
                    return raw;
                }

                public void setRaw(Raw raw) {
                    this.raw = raw;
                }

                // Classe interne pour les informations brutes
                public static class Raw{
                    public String getTwitter() {
                        return twitter;
                    }

                    public void setTwitter(String twitter) {
                        this.twitter = twitter;
                    }

                    public String getFacebook() {
                        return facebook;
                    }

                    public void setFacebook(String facebook) {
                        this.facebook = facebook;
                    }

                    private String twitter;
                    private String facebook;
                }

            }
        }

        // Classe interne pour la géométrie
        public static class Geometry {
            private Double[] coordinates; // Coordonnées [longitude, latitude]

            // Getters et setters
            public Double[] getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(Double[] coordinates) {
                this.coordinates = coordinates;
            }
        }
    }

}

package com.example.voyageapp.data.responseModel;

import java.util.List;

public class CentreCommercialResponse {
    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public static class Feature {

        private Properties properties;
        private Geometry geometry;

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

        public static class Properties {
            private String name;
            private String country;
            private String city;
            private String formatted;
            private String address_line1;
            private String address_line2;
            private Contact contact;
            private String opening_hours;
            private String website;
            private Facilities facilities;
            private Building building;
            private Commercial commercial;
            private WikiAndMedia wiki_and_media;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getAddress_line1() {
                return address_line1;
            }

            public void setAddress_line1(String address_line1) {
                this.address_line1 = address_line1;
            }

            public String getAddress_line2() {
                return address_line2;
            }

            public void setAddress_line2(String address_line2) {
                this.address_line2 = address_line2;
            }

            public Contact getContact() {
                return contact;
            }

            public void setContact(Contact contact) {
                this.contact = contact;
            }

            public String getOpening_hours() {
                return opening_hours;
            }

            public void setOpening_hours(String opening_hours) {
                this.opening_hours = opening_hours;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public Facilities getFacilities() {
                return facilities;
            }

            public void setFacilities(Facilities facilities) {
                this.facilities = facilities;
            }

            public Building getBuilding() {
                return building;
            }

            public void setBuilding(Building building) {
                this.building = building;
            }

            public Commercial getCommercial() {
                return commercial;
            }

            public void setCommercial(Commercial commercial) {
                this.commercial = commercial;
            }

            public WikiAndMedia getWiki_and_media() {
                return wiki_and_media;
            }

            public void setWiki_and_media(WikiAndMedia wiki_and_media) {
                this.wiki_and_media = wiki_and_media;
            }

            public static class Contact {
                private String phone;

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }
            }

            public static class Facilities {
                private boolean wheelchair;
                private boolean toilets;

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

            public static class Building {
                private String type;
                private int start_date;
                private int height;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getStart_date() {
                    return start_date;
                }

                public void setStart_date(int start_date) {
                    this.start_date = start_date;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }

            public static class Commercial {
                private String type;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }

            public static class WikiAndMedia {
                private String wikidata;
                private String wikipedia;

                public String getWikidata() {
                    return wikidata;
                }

                public void setWikidata(String wikidata) {
                    this.wikidata = wikidata;
                }

                public String getWikipedia() {
                    return wikipedia;
                }

                public void setWikipedia(String wikipedia) {
                    this.wikipedia = wikipedia;
                }
            }
        }

        public static class Geometry {
            private Double[] coordinates;

            public Double[] getCoordinates() {
                return coordinates;
            }

            public void setCoordinates(Double[] coordinates) {
                this.coordinates = coordinates;
            }
        }
    }
}

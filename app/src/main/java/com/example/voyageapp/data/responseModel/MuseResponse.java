package com.example.voyageapp.data.responseModel;

import java.util.List;

public class MuseResponse {
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
            private Contact contact;
            private String opening_hours;
            private String website;
            private String operator;
            private Heritage heritage;
            private Facilities facilities;
            private Building building;
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

            public String getOperator() {
                return operator;
            }

            public void setOperator(String operator) {
                this.operator = operator;
            }

            public Heritage getHeritage() {
                return heritage;
            }

            public void setHeritage(Heritage heritage) {
                this.heritage = heritage;
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

            public static class Heritage {
                private int level;
                private String ref;
                private int inscription_date;

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getRef() {
                    return ref;
                }

                public void setRef(String ref) {
                    this.ref = ref;
                }

                public int getInscription_date() {
                    return inscription_date;
                }

                public void setInscription_date(int inscription_date) {
                    this.inscription_date = inscription_date;
                }
            }

            public static class Facilities {
                private boolean wheelchair;

                public boolean isWheelchair() {
                    return wheelchair;
                }

                public void setWheelchair(boolean wheelchair) {
                    this.wheelchair = wheelchair;
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

            public static class WikiAndMedia {
                private String wikidata;
                private String wikipedia;
                private String wikimedia_commons;

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

                public String getWikimedia_commons() {
                    return wikimedia_commons;
                }

                public void setWikimedia_commons(String wikimedia_commons) {
                    this.wikimedia_commons = wikimedia_commons;
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

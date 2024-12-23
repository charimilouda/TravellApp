package com.example.voyageapp.data.responseModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParcResponse {
    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public static class Feature{
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
            private String website;
            private String opening_hours;
            private Facilities facilities;
            private Heritage heritage;
            private Map<String, String> name_international = new HashMap<>();
            private DatasourceRaw datasource;

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

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getOpening_hours() {
                return opening_hours;
            }

            public void setOpening_hours(String opening_hours) {
                this.opening_hours = opening_hours;
            }

            public Facilities getFacilities() {
                return facilities;
            }

            public void setFacilities(Facilities facilities) {
                this.facilities = facilities;
            }

            public Heritage getHeritage() {
                return heritage;
            }

            public void setHeritage(Heritage heritage) {
                this.heritage = heritage;
            }

            public Map<String, String> getName_international() {
                return name_international;
            }

            public void setName_international(Map<String, String> name_international) {
                this.name_international = name_international;
            }

            public DatasourceRaw getDatasource() {
                return datasource;
            }

            public void setDatasource(DatasourceRaw datasource) {
                this.datasource = datasource;
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

            public static class Heritage {
                private int level;

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }
            }

            public static class DatasourceRaw {
                private Raw raw;

                public Raw getRaw() {
                    return raw;
                }

                public void setRaw(Raw raw) {
                    this.raw = raw;
                }

                public static class Raw {
                    private String image;
                    private String image_panorama;

                    public String getImage() {
                        return image;
                    }

                    public void setImage(String image) {
                        this.image = image;
                    }

                    public String getImage_panorama() {
                        return image_panorama;
                    }

                    public void setImage_panorama(String image_panorama) {
                        this.image_panorama = image_panorama;
                    }
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


package com.example.voyageapp.data.responseModel;

import java.util.List;

public class UnsplashResponse {

    private int total; // Total d'images
    private int total_pages; // Nombre total de pages
    private List<Result> results; // Liste des résultats

    public static class Result {
        private String id; // ID de l'image
        private Urls urls; // URLs de l'image

        public static class Urls {
            private String regular; // URL de l'image normale
            private String small; // URL de l'image petite
            private String thumb; // URL de la miniature

            // Getters et Setters
            public String getRegular() {
                return regular;
            }

            public void setRegular(String regular) {
                this.regular = regular;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }

        // Getters et Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Urls getUrls() {
            return urls;
        }

        public void setUrls(Urls urls) {
            this.urls = urls;
        }
    }

    // Getters et Setters
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}

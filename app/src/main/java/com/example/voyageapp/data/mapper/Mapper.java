package com.example.voyageapp.data.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.voyageapp.data.model.Cafe;
import com.example.voyageapp.data.model.CentreCommercial;
import com.example.voyageapp.data.model.Hotel;
import com.example.voyageapp.data.model.Musee;
import com.example.voyageapp.data.model.Parc;
import com.example.voyageapp.data.model.Restaurant;
import com.example.voyageapp.data.responseModel.CafeResponse;
import com.example.voyageapp.data.responseModel.CentreCommercialResponse;
import com.example.voyageapp.data.responseModel.HotelsResponse;
import com.example.voyageapp.data.responseModel.MuseResponse;
import com.example.voyageapp.data.responseModel.ParcResponse;
import com.example.voyageapp.data.responseModel.RestaurantResponse;

public interface Mapper {
    public static List<Hotel> ResponseToHotels(HotelsResponse response) {
        List<Hotel> hotels = new ArrayList<>();
        for (HotelsResponse.Feature feature : response.getFeatures()) {
            HotelsResponse.Feature.Properties props = feature.getProperties();
            HotelsResponse.Feature.Geometry geom = feature.getGeometry();

            Hotel hotel = new Hotel();

            hotel.setName(props.getName());
            hotel.setAddress(props.getFormatted());
            hotel.setCountry(props.getCountry());
            if (props.getOpening_hours()!=null){
                hotel.setOpening_hours(props.getOpening_hours());
            }
            hotel.setCity(props.getCity());
            hotel.setWebsite(props.getWebsite());
            hotel.setPhone(props.getPhone());
            hotel.setWheelchairAccessible(props.isWheelchair());
            hotel.setBuildingLbevels(props.getBuilding() != null ? props.getBuilding().getLevels() : 0);

            //geo

            hotel.setLongitude(geom.getCoordinates()[0]);
            hotel.setLatitude(geom.getCoordinates()[1]);

            hotels.add(hotel);
        }
        return hotels;
    }

    public static List<Restaurant> ResponseToRestaurants(RestaurantResponse response) {
        List<Restaurant> restaurants = new ArrayList<>();
        for (RestaurantResponse.Feature feature : response.getFeatures()) {
            RestaurantResponse.Feature.Properties properties = feature.getProperties();
            RestaurantResponse.Feature.Geometry geom = feature.getGeometry();
            Restaurant restaurant = new Restaurant();

            // Mapping des propriétés depuis le Feature
            restaurant.setName(properties.getName());
            restaurant.setPhone(properties.getContact() != null ? properties.getContact().getPhone() : "");
            restaurant.setEmail(properties.getContact() != null ? properties.getContact().getEmail() : "");
            restaurant.setAddress(properties.getFormatted());
            restaurant.setCountry(properties.getCountry());
            restaurant.setCity(properties.getCity());
            restaurant.setDescription(properties.getDescription());
            if (properties.getOpeningHours()!=null){
                restaurant.setOpeningHours(properties.getOpeningHours());
            }

            // Accessibilité
            if (properties.getFacilities() != null) {
                restaurant.setWheelchairAccessible(properties.getFacilities().isWheelchair());
                restaurant.setWheelchairToilets(properties.getFacilities().isToilets());
            }

            // Réseaux sociaux
            restaurant.setTwitterHandle(properties.getDatasource().getRaw().getTwitter());
            restaurant.setFacebookPage(properties.getDatasource().getRaw().getFacebook());

            // geom

            restaurant.setLongitude(geom.getCoordinates()[0]);
            restaurant.setLatitude(geom.getCoordinates()[1]);

            // Ajouter à la liste
            restaurants.add(restaurant);
        }

        return restaurants;
    }

    public static List<Cafe> ResponseToCafe(CafeResponse response){
        List<Cafe> cafes = new ArrayList<>();


        for (CafeResponse.Feature feature : response.getFeatures()) {
            CafeResponse.Feature.Properties properties = feature.getProperties();
            CafeResponse.Feature.Geometry geom = feature.getGeometry();
            Cafe cafe = new Cafe();

            // Mapping des propriétés depuis le Feature
            cafe.setName(properties.getName());
            cafe.setPhone(properties.getPhone());
            cafe.setCountry(properties.getCountry());
            cafe.setCity(properties.getCity());
            cafe.setInternationalName(properties.getName_international());
            cafe.setPhone(properties.getContact() != null ? properties.getContact().getPhone() : "");
            cafe.setWebsite(properties.getWebsite());
            cafe.setAddress(properties.getFormatted());

            if (properties.getOpening_hours()!=null){
                cafe.setOpeningHours(properties.getOpening_hours());
            }
            cafe.setWebsite(properties.getWebsite());

            // Accessibilité
            if (properties.getFacilities() != null) {
                cafe.setWheelchairAccessible(properties.getFacilities().isWheelchair());
                cafe.setInternetAccess(properties.getFacilities().isInternet_access());
                cafe.setAirConditioning(properties.getFacilities().isAir_conditioning());
                cafe.setOutdoorSeating(properties.getFacilities().isOutdoor_seating());
            }

            //geom

            cafe.setLongitude(geom.getCoordinates()[0]);
            cafe.setLatitude(geom.getCoordinates()[1]);

            // Lien Wikidata
            cafe.setWikidataLink(properties.getWiki_and_media() != null ? properties.getWiki_and_media().getWikidata() : "");

            // Ajouter à la liste
            cafes.add(cafe);
        }

        return cafes;

    }

    public static List<CentreCommercial> ResponseToCentreCommercial(CentreCommercialResponse response){
        List<CentreCommercial> centres = new ArrayList<>();

        for (CentreCommercialResponse.Feature feature : response.getFeatures()) {
            CentreCommercialResponse.Feature.Properties properties = feature.getProperties();
            CentreCommercialResponse.Feature.Geometry geom = feature.getGeometry();
            CentreCommercial centre = new CentreCommercial();

            centre.setName(properties.getName());
            centre.setCountry(properties.getCountry());
            centre.setCity(properties.getCity());
            centre.setPhone(properties.getContact() != null ? properties.getContact().getPhone() : "");
            if (properties.getOpening_hours()!=null){
                centre.setOpeningHours(properties.getOpening_hours());
            }            centre.setWebsite(properties.getWebsite());
            centre.setAddress(properties.getFormatted());

            // falities informations
            if (properties.getFacilities() != null){
                centre.setWheelchairAccessible(properties.getFacilities().isWheelchair());
                centre.setHasToilets(properties.getFacilities().isToilets());
            }

            centre.setBuildingType(properties.getBuilding() != null ? properties.getBuilding().getType() : "");
            centre.setBuildingHeight(properties.getBuilding() != null ? properties.getBuilding().getHeight() : 0);
            centre.setStartDate(properties.getBuilding() != null ? properties.getBuilding().getStart_date() : 0);
            centre.setCommercialType(properties.getCommercial() != null ? properties.getCommercial().getType() : "");

            // Lien Wikidata et Wikipedia
            centre.setWikidataLink(properties.getWiki_and_media() != null ? properties.getWiki_and_media().getWikidata() : "");
            centre.setWikipediaLink(properties.getWiki_and_media() != null ? properties.getWiki_and_media().getWikipedia() : "");

            //geom
            centre.setLongitude(geom.getCoordinates()[0]);
            centre.setLatitude(geom.getCoordinates()[1]);

        }

        return centres;

    }

    public static List<Musee> ResponseToMusee(MuseResponse response){
        List<Musee> musees = new ArrayList<>();

        for (MuseResponse.Feature feature : response.getFeatures()){
            MuseResponse.Feature.Properties properties = feature.getProperties();
            MuseResponse.Feature.Geometry geom = feature.getGeometry();
            Musee musee = new Musee();

            musee.setName(properties.getName());
            musee.setAddress(properties.getFormatted());
            musee.setCity(properties.getCity()); // Latitude
            musee.setCountry(properties.getCountry()); // Longitude
            musee.setWebsite(properties.getWebsite());
            if (properties.getOpening_hours()!=null){
                musee.setOpeningHours(properties.getOpening_hours());
            }
            // Propriétés spécifiques à Musee
            musee.setPhone(properties.getContact() != null ? properties.getContact().getPhone() : "");
            musee.setOperator(properties.getOperator());

            if (properties.getFacilities() != null) {
                musee.setWheelchairAccessible(properties.getFacilities().isWheelchair());
            }

            if (properties.getHeritage() != null) {
                musee.setHeritageLevel(properties.getHeritage().getLevel());
                musee.setHeritageRef(properties.getHeritage().getRef());
                musee.setHeritageInscriptionDate(properties.getHeritage().getInscription_date());
            }

            if (properties.getBuilding() != null) {
                musee.setBuildingType(properties.getBuilding().getType());
                musee.setBuildingHeight(properties.getBuilding().getHeight());
                musee.setBuildingStartDate(properties.getBuilding().getStart_date());
            }

            if (properties.getWiki_and_media() != null) {
                musee.setWikidataLink(properties.getWiki_and_media().getWikidata());
                musee.setWikipediaLink(properties.getWiki_and_media().getWikipedia());
                musee.setWikimediaCommons(properties.getWiki_and_media().getWikimedia_commons());
            }

            // geom

            musee.setLongitude(geom.getCoordinates()[0]);
            musee.setLatitude(geom.getCoordinates()[1]);

            musees.add(musee);
        }


        return musees;
    }

    public static List<Parc> ResponseToParc(ParcResponse response){
        List<Parc> parcs = new ArrayList<>();

        for (ParcResponse.Feature feature : response.getFeatures()) {
            ParcResponse.Feature.Properties properties = feature.getProperties();
            ParcResponse.Feature.Geometry geom = feature.getGeometry();

            Parc parc = new Parc();

            // Propriétés héritées de Lieu
            parc.setName(properties.getName());
            parc.setAddress(properties.getFormatted());
            parc.setCountry(properties.getCountry()); // Latitude
            parc.setCity(properties.getCity()); // Longitude
            parc.setWebsite(properties.getWebsite());
            if (properties.getOpening_hours()!=null){
                parc.setOpeningHours(properties.getOpening_hours());
            }

            // Propriétés spécifiques à Parc

            if (properties.getFacilities() != null) {
                parc.setWheelchairAccessible(properties.getFacilities().isWheelchair());
            }

            if (properties.getHeritage() != null) {
                parc.setHeritageLevel(properties.getHeritage().getLevel());
            }

            if (properties.getDatasource() != null && properties.getDatasource().getRaw() != null) {
                parc.setImageUrl(properties.getDatasource().getRaw().getImage());
                parc.setPanoramaImageUrl(properties.getDatasource().getRaw().getImage_panorama());
            }

            if (properties.getName_international() != null) {
                parc.setNameInInternationalLanguages(properties.getName_international());
            }

            // geom
            parc.setLongitude(geom.getCoordinates()[0]);
            parc.setLatitude(geom.getCoordinates()[1]);

            parcs.add(parc);
        }

        return parcs;
    }
}
